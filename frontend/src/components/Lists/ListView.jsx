import React, { Fragment, useContext, useEffect } from 'react'
import { ListStore, HOST_API } from './ListProvider';
import TodoProvider from '../Todos/TodoProvider';
import TodoForm from '../Todos/TodoForm';
import TodoView from '../Todos/TodoView';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';


const ListView = () => {
    const { dispatch, state } = useContext(ListStore);

    useEffect(() => {
        fetch(HOST_API + "/lists")
        .then((response) => response.json())
        .then((list) => {
            dispatch({ type: "update-list", list });
        })
        .catch(() => {});
    }, [state.list.length, dispatch]);

    const onDelete = (id) => {
        fetch(HOST_API + "/lists/" + id, {
            method: "DELETE",
        }).then(() => {
            dispatch({ type: "delete-item", id });
        });
    };

    const onEdit = (list) => {
        dispatch({ type: "edit-item", item: list });
    };

    return (
        <Fragment>
            {state.list.map((list) => { return (
            <div key={list.id}>
                <div className='list-container'>
                    <div className='list-header'>
                        <h3>ID: <span>{list.id}</span></h3>
                        <h3>Name: <span>{list.name}</span></h3>
                        <button className='list-btn-delete' onClick={() => onDelete(list.id)}><FontAwesomeIcon icon={faTrash} /> DELETE</button>
                        <button className='list-btn-edit' onClick={() => onEdit(list)}><FontAwesomeIcon icon={faEdit} /> EDIT</button>
                    </div>
                    <TodoProvider>
                        <div className='todo-container'>
                            <TodoForm listId = {list.id} />
                            <TodoView listId = {list.id} />
                        </div>
                    </TodoProvider>
                </div>
            </div>
            );
            })}
        </Fragment>
    );
}
 
export default ListView;