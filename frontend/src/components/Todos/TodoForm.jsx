import React, { useRef, useContext, useState } from 'react';
import { Store, HOST_API } from './TodoProvider';

const TodoForm = () => {
    const formRef = useRef(null);
    const { dispatch, state: { item } } = useContext(Store);
    const [ state, setState ] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: null,
            isComplete: false
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
            formRef.current.reset();
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: item.id,
            isComplete: item.isComplete
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
            formRef.current.reset();
        });
    };

    return (
        <form ref={formRef}>
            <input type="text" name="name" defaultValue={item.name}
            onChange={(event) => {
                setState({ ...state, name: event.target.value });
            }}></input>
            {!item.id && <button onClick={onAdd}>Add</button>}
            {item.id && <button onClick={onEdit}>Edit</button>}
        </form>
    );
}
 
export default TodoForm;