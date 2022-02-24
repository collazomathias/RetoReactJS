import React, { useContext, useEffect } from 'react'
import { Store, HOST_API } from './TodoProvider';


const TodoView = () => {
    const { dispatch, state } = useContext(Store);

    useEffect(() => {
        fetch(HOST_API + "/todos")
        .then((response) => response.json())
        .then((list) => {
            dispatch({ type: "update-list", list });
        });
    }, [state.list.length, dispatch]);

    const onDelete = (id) => {
        fetch(HOST_API + "/todos/" + id, {
            method: "DELETE",
        }).then((list) => {
            dispatch({ type: "delete-item", id });
        });
    };

    const onEdit = (todo) => {
        dispatch({ type: "edit-item", item: todo });
    };

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>It is complete?</td>
                    </tr>
                </thead>
                <tbody>
                    {state.list.map((todo) => { return (
                    <tr key={todo.id}>
                        <td>{todo.id}</td>
                        <td>{todo.name}</td>
                        <td>{todo.isComplete ? 'Si' : 'No'}</td>
                        <td><button onClick={() => onDelete(todo.id)}>Delete</button></td>
                        <td><button onClick={() => onEdit(todo)}>Edit</button></td>
                    </tr> );
                    })}
                </tbody>
            </table>
        </div>
    );
}
 
export default TodoView;