import React, { Fragment, useContext, useEffect } from 'react'
import { TodoStore, HOST_API } from './TodoProvider';


const TodoView = (props) => {
    const { dispatch, state } = useContext(TodoStore);

    useEffect(() => {
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

    const changeStatus = (id) => {
        fetch(HOST_API + "/todos/status/" + id, {
            method: "PUT",
        })
        .catch(() => {});
    }

    return (
        <Fragment>
            {
                (state.list.length > 0) ?
                    <div className='todo-header-info'>
                        <h3>ID</h3>
                        <h3>Name</h3>
                        <h3>It is complete?</h3>
                        <h3></h3>
                        <h3></h3>
                    </div> 
                : <h3 className='empty-text'>Empty list.</h3>
            }
            {state.list.map((todo) => { return (
                <div key={todo.id} className='todo-container-info'>
                    <h3>{todo.id}</h3>
                    <h3>{todo.name}</h3>
                    <div className='todo-btn-status-container'>
                        {(todo.complete === true) ? 
                            <button className='todo-btn-status status-complete' onClick={() => changeStatus(todo.id)}>Si</button>
                            : 
                            <button className='todo-btn-status status-incomplete' onClick={() => changeStatus(todo.id)}>No</button>
                        }
                    </div>
                    <button className='todo-btn-delete' onClick={() => onDelete(todo.id)}>Delete</button>
                    <button className='todo-btn-edit' onClick={() => onEdit(todo)}>Edit</button>
                </div> );
            })}
        </Fragment>
    );
}
 
export default TodoView;