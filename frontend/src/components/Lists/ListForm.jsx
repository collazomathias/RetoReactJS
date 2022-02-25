import React, { useRef, useContext, useState } from 'react';
import { ListStore, HOST_API } from './ListProvider';

const ListForm = () => {
    const formListRef = useRef(null);
    const { dispatch, state: { item } } = useContext(ListStore);
    const [ state, setState ] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: null,
        }
        fetch(HOST_API + "/lists", {
            method: "POST",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((list) => {
            dispatch({ type: "add-item", item: list });
            setState({ name: "" });
            formListRef.current.reset();
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        const request = {
            name: state.name,
            id: item.id,
        };
    
        fetch(HOST_API + "/lists/ "+item.id, {
            method: "PUT",
            body: JSON.stringify(request),
            headers: { "Content-Type": "application/json" }
        })
        .then(response => response.json())
        .then((list) => {
            dispatch({ type: "update-item", item: list });
            setState({ name: "" });
            formListRef.current.reset();
        });
    };

    return (
        <form ref={formListRef} className='list-form'>
            <input type='text' name='name' placeholder='List name...' defaultValue={item.name}
            onChange={(event) => {
                setState({ ...state, name: event.target.value });
            }}></input>
            {!item.id && <button onClick={onAdd}>Add list</button>}
            {item.id && <button onClick={onEdit}>Edit list</button>}
        </form>
    );
}
 
export default ListForm;