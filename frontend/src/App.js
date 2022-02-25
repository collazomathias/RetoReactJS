import React, { Fragment } from 'react';
import ListProvider from './components/Lists/ListProvider';
import ListForm from './components/Lists/ListForm';
import ListView from './components/Lists/ListView';


function App() {
    return (
        <Fragment>
            <div className='title-container'>
                <h1 className='title'>TODO LISTS</h1>
            </div>
            <ListProvider>
                <ListForm />
                <ListView />
            </ListProvider>
        </Fragment>
    );
}

export default App;
