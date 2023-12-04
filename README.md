# Actividad 05 - Películas con Sockets
Se pide hacer dos programas cliente-servidor con sockets e hilos. La aplicación servidora programa consistirá en crear una aplicación que gestione una serie de películas de una biblioteca virtual, la aplicación cliente consumirá dicha aplicación servidora.

Las películas tendrán un ID, un título, un director y un precio. Se encontrarán alojadas en el servidor. Dicho servidor cuando arranque tendrá 5 películas preestablecidas con todos los datos rellenos. Las películas se guardarán en memoria en cualquier tipo de estructura de datos (como puede ser un lista). El servidor deberá estar preparado para que interactúen con él varios clientes (se deberá abrir un hilo por cada cliente).

La aplicación cliente mostrará un menú como el que sigue:

- Consultar película por ID
- Consultar película por título
- Consultar películas por director
- Agregar película
- Salir de la aplicación
- La aplicación se ejecutará hasta que el cliente decida pulsar la opción de “salir de la aplicación”.

Las dos primeras opciones mostrará todos los datos de la película buscando por Id o por titulo.

“Consultar películas por director”. En este caso hay que tener en cuenta que puede haber varias películas por director, por lo que el servidor podrá devolver una lista de películas. Se recomienda pensar en grupo el formato de envío de información.

“Añadir película”. En este caso el cliente pedirá todos los datos de la película y los enviará al servidor para que este lo guarde. La lista en el servidor deberá estar preparada para que solo pueda añadir una película cada hilo a la vez, si algún hilo está agregando una película, los demás hilos deberán de esperar a que el hilo acabe.

El cliente deberá de recoger todos los datos y mandarlos al servidor en un solo envío.