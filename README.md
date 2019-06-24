# Hotel_java
ThoughtWorksInterview

## Explicacion:

###### 1) Para ejecutar:
    a. Con maven (*Preferible) en terminal/cmd
        i.  ) cambiar directorio a:
               cd ~/Hotel_java 
        ii. ) mvn clean install
        iii.) mvn exec:java -Dexec.mainClass=Hotel_java

    b. En cmd windows o en Terminal mac
        i. ) Cambiar directorio a:
            cd ~/Hotel_java/src/main/java

        ii. ) javac Hotel_java.java
        iii.) java Hotel_java
        iv. ) dialogo le pedirá insertar archivo de texto

    c. En IntelliJ IDE
        i. ) importar proyecto
        ii.) ejecutar main en Hotel_java.java

###### 2) Para el diseño:
    Utilicé el patrón de creaciones de diseño Factory Pattern para la creación de hoteles.
    La utilización de este patrón ayuda a la creación de hoteles tan solo especificando su nombre.
    Esto permite que el código sea extensible y menos dependiente de la especifica instancia de un hotel.
    Debido a que hay comportamiento en común entre los Hoteles, la clase HotelBase implementa los métodos que
    todos los Hoteles pueden heredar. Las derivaciones de HotelBase pueden ser creadas con o sin especificar el tipo
    de cliente que va a usarlas (Cliente “reward” o cliente “regular”).
    Añadí la funcionalidad de establecer el tipo de cliente, si a futuro un cliente se convierte en “reward” puede aplicárseles
    las tarifas reducidas usando el método hotel.setCustomerType().

    Para la manipulación de la información que tienen los hoteles creé la clase HotelsAnalyzer. De tal manera es más fácil
    añadir nuevas funcionalidades a futuro.  HotelsAnalyzer provee funcionalidades como obtener el precio de un hotel en determinadas
    fechas, obtener el hotel con más alto ranking, el hotel más barato, el hotel con mayor ranking que es el más barato.
    Todas estas funciones son usadas para entregar el hotel mas barato (objetivo del programa), pero a la vez, al hacer cada función
    más modular el código se vuelve extensible y fácil de testar.

    Para leer archivos, asumiendo que solo se proveerán archivos de texto, creé la clase HotelsReader con el fin de poder aceptar
    nuevos formatos de input a futuro y validarlos. Actualmente, asumí que el único formato a usar es:
    “Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)”. Sin embargo, el código esta listo recibir modificaciones
    y aceptar más formatos. Así también, decidí agregar la opción de aceptar archivos con varias líneas de input con el fin de que
    el programa sea mas potente y sea más fácil de someter a pruebas robustas.

    El código incluye los varios tests que diseñe. La utilización de JOptionPane fue para mejorar la experiencia del usuario.

