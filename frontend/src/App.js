import React, { Children } from 'react';
import TodoForm from './components/Todos/TodoForm';
import TodoView from './components/Todos/TodoView';
import TodoProvider from './components/Todos/TodoProvider';


function App() {
    return (
        <TodoProvider>
            <TodoForm />
            <TodoView />
        </TodoProvider>
    );
}

export default App;
