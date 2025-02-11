# Grantalytics - Interaction Data Aggregate for the Personal Site of Grant DeWaay

Grantalytics Server is a simple data aggregation RESTful service built with Spring Boot that receives requests when users visit and subsequently closes [my personal site](https://grantdewaay.com)
This enables me to see what projects resonate the most with my audience, and what content I can improve on.
Best of all, I am able to see when specific employers visit the site as part of the application process by appending my URL with a path.

Data is stored in MongoDB Atlas, you can connect your own database by referencing the Configuration section of the document.

The following data is collected:

 * The path that is appended after the URL
 * The duration that the page is viewed in seconds
 * Whether the video was played, and if so, how long it was played for
 * Interactions with buttons

Additionally, I implemented rate limiting in order to prevent abuse and excessive spamming of the service.

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
