import _ from 'lodash';
import './style.css';
import 'bulma/css/bulma.min.css';
import '../css/all.css';
import '../css/style.css';

import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(
    <React.StrictMode>
       <App />
    </React.StrictMode>,
    document.getElementById('root')
);

function component() {
    const element = document.createElement('div');
    return element;
}

document.body.appendChild(component());
