# GraphQL Spring Boot Case Study
This story focuses on GraphQL learning in Spring Boot. And finally we will discuss what are the Pros and Cons.When you talk about API design, the first thing that probably comes to mind is Representational State Transfer (REST). A standard for data retrieval from the server, it's based on accessing data by URLs.
REST gave us important concepts for API design stateless servers and structured access to resources. However, since that time APIs have gotten more complex and data-driven affected by the following factors:
*  Efficient data loading, load data that is needed.
*  Rest returns fixed data structure for a URL. The data comes as null and taken out of serialization. But cannot deliver the data that is only needed.
*  Having multiple API endpoints for a CRUD operation. For Ex: A simple use case Book entity can have multiple end points like:
     1. Create/Update Book
     2. Get Book by its ID.
     3. Get Books by Author's name.
     4. Get Books by Publications.
     5. Get Books by Language.
     6. Get Books by year published.

GraphQL, a modern alternative to the REST-based architecture, aims at solving its shortcomings. Unlike REST, GraphQL allows for requesting specific data that a client needs, departing from the fixed data structure approach.

## What is GraphQL?
Developers describe GraphQL as "A data query language and runtime". GraphQL is a data query language and runtime designed and used at Facebook to request and deliver data to mobile and web apps since 2012. It was released as open source around 2015.

GraphQL is a strongly typed runtime that defines common datatypes, e.g. String and Integer and other primitives. Additionally, you can define your own datatypes containing types already defined by GraphQL or one of your own. So you can create complex nested objects and you are not limited to one resource like in REST.

Lets look at sample application. This application is library, contains books, authors, publications and languages.

With the help of annotations, either we can use schema to perform these operations. As we are using Spring boot, thought better to use annotations way.
@GraphQLAPI : which exposes the service/component class as http url.
@GraphQLMutation : Exposing mutations through this annotation.
@GraphQLQuery : Exposing query through this annotation.

Deploy the application and open http://localhost:9002/gui
You will see a playground opened to send requests to the server.

Please see this Link in [Medium.com](https://medium.com/@eresh.zealous/graphql-spring-boot-case-study-b430fa419d25) for complete tuitorial.