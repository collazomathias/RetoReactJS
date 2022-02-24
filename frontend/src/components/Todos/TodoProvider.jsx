import { createContext, useReducer } from "react";
import TodoReducer from "./TodoReducer";


const TodoProvider = ({ children }) => {
    const initialState = {
        list: [],
        item: {}
    };
    const [state, dispatch] = useReducer(TodoReducer, initialState);
    return (
        <Store.Provider value={{ state, dispatch }}>{ children }</Store.Provider>
    );
}

export const HOST_API = "http://localhost:8081/api";
export const Store = createContext();
export default TodoProvider;