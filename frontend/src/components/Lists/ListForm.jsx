import React, { useRef, useContext, useState } from 'react';
import { ListStore, HOST_API } from './ListProvider';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faPlus } from '@fortawesome/free-solid-svg-icons';

const ListForm = () => {
    const formListRef = useRef(null);
    const { dispatch, state: { item } } = useContext(ListStore);
    const [ state, setState ] = useState(item);

    const [ message, setMessage ] = useState(false);

    const onAdd = (event) => {
        event.preventDefault();
        if(state.name.length < 3 || state.name.length > 30){
            setMessage(true);
            return;
        }
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
            setMessage(false);
        });
    }

    const onEdit = (event) => {
        event.preventDefault();
        if(state.name.length < 3 || state.name.length > 30){
            setMessage(true);
            return;
        }
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
            setMessage(false);
        });
    };

    return (
        <form ref={formListRef} className='list-form-container'>
            <div className='list-form'>
                <input type='text' name='name' placeholder='List name...' defaultValue={item.name}
                onChange={(event) => {
                    setState({ ...state, name: event.target.value });
                }}></input>
                {!item.id && <button onClick={onAdd}><FontAwesomeIcon icon={faPlus} /> ADD</button>}
                {item.id && <button onClick={onEdit}><FontAwesomeIcon icon={faEdit} /> EDIT</button>}
            </div>
            <p className={(message === true) ? 'errorMessage show' : 'errorMessage'}>You must enter between 3 and 30 characters.</p>
        </form>
    );
}
 
export default ListForm;