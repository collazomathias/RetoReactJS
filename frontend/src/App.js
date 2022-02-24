import React, { Fragment } from 'react';
import ListProvider from './components/Lists/ListProvider';
import ListForm from './components/Lists/ListForm';
import ListView from './components/Lists/ListView';


function App() {
    return (
        <Fragment>
            <h2>Listas de TODO's</h2>
            <div>
                <ListProvider>
                    <ListForm />
                    <ListView />
                </ListProvider>
            </div>
        </Fragment>
    );
}

export default App;
