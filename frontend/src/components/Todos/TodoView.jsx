import React, { useContext, useEffect } from 'react'
import { TodoStore, HOST_API } from './TodoProvider';


const TodoView = (props) => {
    const { dispatch, state } = useContext(TodoStore);

    useEffect(() => {
        /*fetch(HOST_API + "/todos/list/" + props.listId)*/
        fetch(HOST_API + "/todos/list/" + props.listId)
        .then((response) => response.json())
        .then((list) => {
            dispatch({ type: "update-list", list });
        })
        .catch(() => {});
    }, [state.list.length, dispatch]);

    const onDelete = (id) => {
        fetch(HOST_API + "/todos/" + id, {
            method: "DELETE",
        }).then(() => {
            dispatch({ type: "delete-item", id });
        });
    };

    const onEdit = (todo) => {
        dispatch({ type: "edit-item", item: todo });
    };

    return (
        <div className='flex-column'>
            <div className='flex-row'>
                {
                    (state.list.length > 0) ? 
                    <div className='flex-row'>
                        <h3>ID</h3>
                        <h3>Name</h3>
                        <h3>It is complete?</h3>
                    </div> :
                    <h3>Lista vacía</h3>
                }
            </div>
            <div>
                {state.list.map((todo) => { return (
                    <div key={todo.id} className='flex-row'>
                        <h3>{todo.id}</h3>
                        <h3>{todo.name}</h3>
                        <h3>{todo.isComplete ? 'Si' : 'No'}</h3>
                        <button onClick={() => onDelete(todo.id)}>Delete</button>
                        <button onClick={() => onEdit(todo)}>Edit</button>
                    </div> );
                })}
            </div>
        </div>
    );
}
 
export default TodoView;