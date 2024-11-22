# 🖥️ Aplicación de Escritorio Java - Arquitectura en 3 Capas

Este proyecto es una aplicación de escritorio desarrollada en Java que implementa una arquitectura en 3 capas: lógica, persistencia y gráfica. La aplicación utiliza patrones de diseño como Value Object, Abstract Factory, Fachada, y DAO (Data Access Object). La persistencia de datos se realiza tanto en un archivo local como en una base de datos MySQL.

## 📋 Características del Proyecto

### Arquitectura en 3 Capas:
- Capa de Presentación (Gráfica): Interfaz de usuario para interactuar con la aplicación.
- Capa de Lógica: Contiene la lógica de negocio y reglas de la aplicación.
- Capa de Persistencia: Maneja el acceso y almacenamiento de datos, ya sea en un archivo local o en una base de datos MySQL.

### Patrones de Diseño Implementados:
- Value Object (VO): Representa objetos inmutables que contienen valores.
- Abstract Factory: Facilita la creación de familias de objetos relacionados sin especificar sus clases concretas.
- Fachada: Proporciona una interfaz simplificada para interactuar con subsistemas complejos.
- DAO (Data Access Object): Abstrae el acceso a los datos, permitiendo operaciones de persistencia sin exponer los detalles de la base de datos.

### Persistencia Híbrida:
- Archivo Local: Guarda los datos en un archivo para facilitar la persistencia local.
- Base de Datos MySQL: Almacena los datos en una base de datos relacional para garantizar la integridad y escalabilidad.

## 🛠️ Tecnologías Utilizadas
- Java: Lenguaje de programación principal.
- Swing: Biblioteca para la creación de la interfaz gráfica de usuario (GUI).
- MySQL: Base de datos relacional para almacenamiento de datos.
- JDBC: Para la conexión y manipulación de la base de datos MySQL.

## 📖 Patrones de Diseño Explicados
- Value Object: Utilizado para representar objetos que no tienen identidad y son inmutables, como fechas o coordenadas.
- Abstract Factory: Permite crear diferentes implementaciones de persistencia (archivo o MySQL) de manera transparente.
- Fachada: Simplifica el acceso a la lógica de negocio, proporcionando una única clase que interactúa con los subsistemas.
- DAO (Data Access Object): Aísla la lógica de acceso a datos, permitiendo cambios en la fuente de datos sin afectar otras capas.
