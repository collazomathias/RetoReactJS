## ReactJS & Springboot - Reto final - Training Desarrollo SofkaU

El reto está realizado en ReactJS y Springboot, por lo cual es necesario tener instalado node en nuestro ordenador, y consecuentemente ejecutar los siguientes comandos:
>##### 1. npm install
>##### 2. npm install --save @fortawesome/react-fontawesome

Métodos utilizados:
|Metodo HTTP|GET|POST|DELETE|PUT|
|-----------|---|----|------|---|
|/todos|Listar los TODOs|Crear nuevo TODO|Borrar todos los TODOs|Error|
|/todos/{id}|Listar el TODO con ID {id}|Error|Borrar el TODO con ID {id}|Modificar el TODO con ID {id}|
|/todos/list/{idTodoList}|Listar los TODOs con ID de Lista {idTodoList}|Error|Error|Error|
|todos/status/{id}|Error|Error|Error|Modifica el estado "Complete" del TODO con ID {id}|
||||||
|/lists|Listar las TodoLISTs|Crear nueva TodoLIST|Borrar todos los TODOs y TodoLISTs|Error|
|/lists/{id}|Listar la TodoLIST con ID {id}|Error|Borrar la TodoLIST con ID {id}|Modificar la TodoLIST con ID {id}|

Video explicando el funcionamiento del sistema:

[![Alt text](https://img.youtube.com/vi/8Q-8LWkBRyM/0.jpg)](https://www.youtube.com/watch?v=8Q-8LWkBRyM)