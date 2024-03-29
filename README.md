# InjectQ

A Spring Boot extension allowing for injecting the SQL query strings from the files on the classpath into the Spring-managed 
components.

[![Build Status](https://travis-ci.org/jolice/injectq.svg?branch=master)](https://travis-ci.org/jolice/injectq)
[![codecov](https://codecov.io/gh/jolice/injectq/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/injectq)
[![](https://jitpack.io/v/jolice/injectq.svg)](https://jitpack.io/#jolice/injectq)

## Dependency

This project is distributed via JitPack. Register a JitPack repository at your pom.xml:

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

And add the following dependency:

```xml
<dependency>
    <groupId>com.github.jolice</groupId>
    <artifactId>injectq</artifactId>
    <version>1.0</version>
</dependency>
```

## Injecting a query

### Enabling the extension

First of all, enable the InjectQuery by placing the `@EnableInjectQuery` annotation at some configuration or the main class of your application:

```java
@EnableInjectQuery
@Configuration
public class MyApplicationConfiguration {
```

This step is *mandatory* for importing the extension components into your application.

### Basic injection

The query injection is carried out via `@SqlQuery` annotation. 
To inject a query, place `@SqlQuery` annotation at one of the supported injection points: field, method or constructor and 
specify the name of the file storing your query without the extension:

```java
@SqlQuery("all-employees")
private String allEmployees;
```

The query string will be read from `all-employees.sql` file on your classpath, in most cases (for example, when using Maven)
from the `resources` directory.

### Constructor and method injection

You are free to inject your queries the same way as supplying the regular dependencies, including the constructor and
setter options:

```java
public class EmployeeRepository {

    private final String getAllEmployeesQuery;

    public EmployeeRepository(@SqlQuery("all-employees") String getAllEmployeesQuery) {
        this.getAllEmployeesQuery = getAllEmployeesQuery;
    }
}
```

Setter injection is pretty straightforward as well:

```java
@SqlQuery("all-employees")
public void setAllEmployeesQuery(String allEmployeesQuery) {
    this.allEmployeesQuery = allEmployeesQuery;
}
```

#### The preferred injection option

There's no flawless and unequivocally preferable injection approach, each has its proc and cons. 

- The field injection seems handy enough at first glance, but has a disadvantage of complicating the unit testing,
namely setting the private field value outside of the Spring context.
- The method injection implies the mutability and exposes many unwanted methods serving only for the DI container.
- The constructor injection may seem as the best option among others, however, the drawback is that injecting many queries
will lead to the bloat constructors.

## Configuration

The extension may be configured via the `@EnableInjectQuery` annotation. Currently supported configuration
options are listed below.

#### Path

To set a default root path for all queries, use the path option:

```java
@EnableInjectQuery(path = "queries")
```

The query files will be always read under the `queries` directory, e.g:

```java
@SqlQuery("all-employees")
private String getAllEmployeesQuery; 
```

The query will be read from the resource located at `queries/all-employees.sql`.

#### Extension

For some reason, you may also want to change the default script file extension :

```java
@EnableInjectQuery(extension = "txt")
```

Thus, the query for the following injection point:

```java
@SqlQuery("all-employees")
private String getAllEmployeesQuery; 
```

Will be read from the file located at `all-employees.txt`.







