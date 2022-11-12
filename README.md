# Software Testing Tutorial - Learn Unit Testing and Integration Testing

## ¿Cómo agregar JUnit 5?
En realidad **no tenemos que hacer nada**, ya que cuando creamos 
el proyecto se agrega la dependencia para el Test, cuyo ámbito
está definido en test, es decir, únicamente esta dependencia será
usada para fines de pruebas:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

Por otro lado, si presionamos control + click en el nombre
del artifactId (spring-boot-starter-test), este nos llevará a 
un archivo xml, y revisando ese archivo veremos que viene
incluida la dependencia de **assertj**

```
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.18.1</version>
    <scope>compile</scope>
</dependency>
```

Según la página oficial de [**assertj**](https://joel-costigliola.github.io/assertj/assertj-core.html) menciona que: 
*"AssertJ core es una biblioteca Java que proporciona una interfaz fluida para 
escribir aserciones. Su principal objetivo es Mejore la legibilidad del código 
de prueba y facilite el mantenimiento de las pruebas."*, que es lo que precisamente
usaremos para hacer nuestras pruebas más entendibles.

Bueno, eso por un lado, por otro lado, si seguimos revisando las dependencias
nos encontraremos con la de **JUnit5**

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.7.1</version>
    <scope>compile</scope>
</dependency>
```

y también con **Mockito**

```
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.6.28</version>
    <scope>compile</scope>
</dependency>
```

## CONCLUSIÓN
**No tenemos que agregar ni instalar nada, porque por defecto con**
**la dependencia que nos provee Spring Boot al crear un nuevo proyecto**
**(spring-boot-starter-test) ya estaríamos listos para empezar a hacer pruebas**