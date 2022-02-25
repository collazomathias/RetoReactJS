import React, { useRef, useContext, useState } from 'react';
import { TodoStore, HOST_API } from './TodoProvider';

const TodoForm = (props) => {
    const formTodoRef = useRef(null);
    const { dispatch, state: { item } } = useContext(TodoStore);
    const [ state, setState ] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: null,
            isComplete: false,
            idTodoList: props.listId
        }
        fetch(HOST_API + "/todos", {
            method: "POST",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((todo) => {
            dispatch({ type: "add-item", item: todo });
            setState({ name: "" });
            formTodoRef.current.reset();
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: item.id,
            isComplete: item.isComplete,
            idTodoList: state.idTodoList
        };
        fetch(HOST_API + "/todos/ "+item.id, {
            method: "PUT",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((todo) => {
            dispatch({ type: "update-item", item: todo });
            setState({ name: "" });
            formTodoRef.current.reset();
        });
    };

    return (
        <form ref={formTodoRef} className='todo-header'>
            <input type="text" name="name" placeholder='What you have to do?' defaultValue={item.name}
            onChange={(event) => {
                setState({ ...state, name: event.target.value });
            }}></input>
            {!item.id && <button onClick={onAdd}>Add todo</button>}
            {item.id && <button onClick={onEdit}>Edit</button>}
        </form>
    );
}
 
export default TodoForm;