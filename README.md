# lawson-busch-input-demo

### Contains examples of parameterize queries

These queries have issues being mapped by DGS's DefaultInputObjectMapper. The default object mapper
appears to have problems with parameterized types with an upper bound, as well as concrete
implementations of parameterized types that use a List implementation.

The java docs explain what each query / mutation is designed to test, and what the
expected return value for each is.

This demo uses the following technologies:

- Spring Boot 2.7
- DGS version 5.2.2
- Java 17

To run this demo locally:

1. Install the latest version of IntelliJ idea
2. run `mvn clean install` from the terminal
3. run the `InputDemoApplication` from IntelliJ Idea
4. Navigate to `http://localhost:8080/graphiql` to test queries and mutation

Below are example calls:

```graphql
#fails with generic implementation and ? operator
mutation testGenericType($props: TaskPropertyInput!) {
    testGenericFieldTypeNotFound(taskProperties: $props) {
        fields {
            type
            fieldName
        }
    }
}

#works as expected when resolved as a return value
query getFakeGeneric {
    getFakeGeneric {
        fields {
            fieldName
            type
            value
        }
    }
}

#workaround that unwraps the object structure
mutation testWorkAround($fields: [TaskConfigurationInput!]!) {
    testGenericWorkAround(taskConfigurationFields: $fields) {
        fieldName
        type
        value
    }
}

#fails even with a concrete type for the implementation
mutation testConcrete($wrapper: ConcretePropertyInput!) {
    testGenericFieldListNotWrappedWhenConcreteInput(wrapper: $wrapper) {
        properties {
            fields {
                fieldName,
                type,
                value
            }
        }
    }
}
```

With the variable setup:

```json
{
  "props": {
    "fields": [
      {
        "fieldName": "test field",
        "type": "STRING",
        "value": "string value"
      }
    ]
  },
  "fields": [
    {
      "fieldName": "test field",
      "type": "STRING",
      "value": "string value"
    }
  ],
  "wrapper": {
    "properties": {
      "fields": [
        {
          "fieldName": "test field",
          "type": "STRING",
          "value": "string value"
        }
      ]
    }
  }
}
```