A)
The customer:

|  HttpMethod  |                      URL path                      |  HTTP status  |            description            |
|:------------:|:--------------------------------------------------:|:-------------:|:---------------------------------:|
|     Get      |            localhost:8080/api/customers            |      200      |       get all the customers       |
|     Get      |        localhost:8080/api/customers/id/{id}        |      200      |      get the customer by id       |
|     Post     |            localhost:8080/api/customers            |      201      |       create a new customer       |
|     Put      |         localhost:8080/api/customers/{id}          |      200      |  update existing customer by id   |
|    Delete    |         localhost:8080/api/customers/{id}          |      200      |  delete existing customer by id   |
|     Get      | localhost:8080/api/customers/firstname/{firstname} |      200      | get the customer by the firstname |
|     Get      |     localhost:8080/api/customers/price/{price}     |      200      |     get the customer by price     |

Order

|  HttpMethod  |                      URL path                      |  HTTP status  |            description            |
|:------------:|:--------------------------------------------------:|:-------------:|:---------------------------------:|
|     Get      |             localhost:8080/api/orders              |      200      |        get all the orders         |
|     Get      |         localhost:8080/api/orders/id/{id}          |      200      |        get the order by id        |
|     Post     |             localhost:8080/api/orders              |      201      |        create a new order         |
|     Put      |           localhost:8080/api/orders/{id}           |      200      |    update existing order by id    |
|    Delete    |           localhost:8080/api/orders/{id}           |      200      |    delete existing order by id    |
|     Get      | localhost:8080/api/orders/customerId/{customer_id} |      200      |    get the orders by customer     |
|     Get      |       localhost:8080/api/orders/ordersToday        |      200      |       get the orders today        |

Products

|  HttpMethod  |                     URL path                      |  HTTP status  |              description              |
|:------------:|:-------------------------------------------------:|:-------------:|:-------------------------------------:|
|     Get      |            localhost:8080/api/products            |      200      |         get all the products          |
|     Get      |        localhost:8080/api/products/id/{id}        |      200      |         get the product by id         |
|     Post     |            localhost:8080/api/products            |      201      |         create a new product          |
|     Put      |         localhost:8080/api/products/{id}          |      200      |     update existing product by id     |
|    Delete    |         localhost:8080/api/products/{id}          |      200      |     delete existing product by id     |
|     Get      | localhost:8080/api/products/firstname/{firstname} |      200      | get the products that are stock able  |
|     Get      |     localhost:8080/api/products/price/{price}     |      200      |    get the product less than price    |

Stock

|  HttpMethod  |                  URL path                  |  HTTP status  |                            description                             |
|:------------:|:------------------------------------------:|:-------------:|:------------------------------------------------------------------:|
|     Get      |         localhost:8080/api/stocks          |      200      |                         get all the stocks                         |
|     Get      |     localhost:8080/api/stocks/id/{id}      |      200      |                        get the stock by id                         |
|     Post     |         localhost:8080/api/stocks          |      201      |                         create a new stock                         |
|     Put      |       localhost:8080/api/stocks/{id}       |      200      |                    update existing stock by id                     |
|    Delete    |       localhost:8080/api/stocks/{id}       |      200      |                    delete existing stock by id                     |
|     Get      |   localhost:8080/api/stocks/name/{name}    |      200      |      Get the quantity in the stock of the product by its name      |
|     Get      | localhost:8080/api/stocks/checkDate/{name} |      200      |   Get last updated date of the product in the stock by its name    |


|  HttpMethod  |                    URL path                     |  HTTP status  |                     description                     |
|:------------:|:-----------------------------------------------:|:-------------:|:---------------------------------------------------:|
|     Get      | localhost:8080/api/product_orders/price/{price} |      200      |     Get all the product_orders by certain price     |
|     Get      |  localhost:8080/api/product_orders/name/{name}  |      200      | Get all the product_orders by certain product name  |

User:

|  HttpMethod  |             URL path              |  HTTP status  |           description            |
|:------------:|:---------------------------------:|:-------------:|:--------------------------------:|
|     Post     |  localhost:8080/api/users/signup  |      201      |        signup to the api         |
| :----------: | :-------------------------------: | :-----------: | :------------------------------: |

Authentication:

|  HttpMethod  |                URL path                 |  HTTP status  |             description             |
|:------------:|:---------------------------------------:|:-------------:|:-----------------------------------:|
|     Post     | localhost:8080/api/token/generate-token |      201      | generate a new token to use the api |

B) 
I built an api by depending on the single responsibility. I created the entity to contact with the database. And the repository 
to provide the CRUD operations. Also, the services interface and services implementation class for the business logic. In addition, 
the resource to enable the user to use the methods of the api resources. Dto for transfer the data between processes. and the 
security to make the api secure by generating a token to use the methods and nobody can use the system without signing up or 
signing and use the token that is generated. Finally, I created swagger code to make a documentation for my api. As a result,
I have a secure api :)  