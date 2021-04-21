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

    // Lodash, currently included via a script, is required for this line to work
    //element.innerHTML = _.join(['Hello', 'webpack'], ' ');
    //element.classList.add('hello');

    return element;
}

document.body.appendChild(component());