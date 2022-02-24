import React, { Fragment, useContext, useEffect } from 'react'
import { ListStore, HOST_API } from './ListProvider';
import TodoProvider from '../Todos/TodoProvider';
import TodoForm from '../Todos/TodoForm';
import TodoView from '../Todos/TodoView';


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
                <div>
                    <div className='flex-row'>
                        <h3>ID: <span>{list.id}</span></h3>
                        <h3>Name: <span>{list.name}</span></h3>
                        <button onClick={() => onDelete(list.id)}>Delete</button>
                        <button onClick={() => onEdit(list)}>Edit</button>
                    </div> 
                </div>
                <div>
                    <TodoProvider>
                        <div className='flex-row'>
                            <h3>TODO's de la lista:</h3>
                            <TodoForm listId = {list.id} />
                        </div>
                        <TodoView listId = {list.id} />
                    </TodoProvider>
                </div>
            </div>
            );
            })}
        </Fragment>
    );
}
 
export default ListView;