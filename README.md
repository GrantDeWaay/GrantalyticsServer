# Grantalytics
## Interaction Data Aggregate for the Personal Site of Grant DeWaay

### Configuration
Before running the application, you need to set up the `application.properties` file.

1. Copy the `application.properties.template` file to a new file named `application.properties`:

```bash
cp application.properties.template application.properties
```

2. Open the `application.properties` file and replace the placeholders with your actual values:
```properties
# Example
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/?retryWrites=true&w=majority
spring.data.mongodb.database=LogEvents
```
3. Save the file and start the application.