# coffee-shop
SE430 - Group 6

## Objective
Create a coffee shop application where customers can log in, select their coffee
orders from a nearby coffee shop that sells the item. Shop A might not carry the item that
the customer is requesting, but another nearby shop B has it on the menu. The app will
function as a mediator between the user and coffee shops that are local to the user. The
application ensures that customers can easily log in, search for coffee items, and place
orders from shops that have the item available (not just the user’s default store).

## Scope
The scope of this project includes the user/shop login, item search, shop availability check, and order placement.
Processes not included in our scope can be described as functionalities pertaining to automated location tracking and payment system.

---

## Building / Running
This project utilizes Java + Maven (JUnit and Mockito planned) for compilation.

First, clone the repository:
```bash
git clone https://github.com/nkh361/coffee-shop.git
```

Then, load the repository with Maven:
```bash
cd coffee-shop
cd CoffeeApp
mvn clean package
```

To run once in CoffeeApp directory:
```bash
mvn spring-boot:run
```
Note, the login configuration is incomplete and utilizes Spring Securtity. This creates a [custom login page and default user](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html).
In the terminal where `mvn spring-boot:run` is called, there will be a response:
```bash
Using generated security password: <RANDOM PASSWORD>
```

To log into the application:
```bash
username: user
password: <RANDOM PASSWORD>
```

---

## Push guidelines
Main branch should only include a clean build.
New features from Jira Tickets should be worked on in new branches, which should be submitted as a pull request when merging.
**Please avoid force merges and rollbacks without consulting team.**

## Avoiding merge conflicts
- Avoid working on multiple unrelated things in one branch. One branch should be treated as one feature.
- Pull often, push often.
- Run tests before merging (regressive testing).
