### URL Shortener API Documentation

This document provides an overview of the URL Shortener API collection named "URL SHORT" and includes instructions for using the endpoints defined in this collection. 

#### Collection Overview
- **Collection Name**: URL SHORT
- **Collection ID**: 18ba81ee-27af-4c52-bbd5-399808b87d37
- **Schema**: [Postman Collection Schema v2.1.0](https://schema.getpostman.com/json/collection/v2.1.0/collection.json)
- **Exporter ID**: 20850921
- **Collection Link**: [URL SHORT Collection](https://lunar-escape-512016.postman.co/workspace/PREMBLY-WORKSPACE~5e032391-d8af-4960-9dc6-13b0994704c5/collection/20850921-18ba81ee-27af-4c52-bbd5-399808b87d37?action=share&source=collection_link&creator=20850921)

#### Prerequisites
- Ensure you have Postman installed on your machine.
- Import the collection using the link provided above.
- Set the following environment variables in Postman:
  - `Auth_Token_Global`: Your authorization token.
  - `BASE_URL`: The base URL of your API (e.g., `http://localhost:8080`).

#### Endpoints

##### 1. Retrieve All URLs
- **Endpoint Name**: All
- **Method**: GET
- **URL**: `{{BASE_URL}}/api/v1/urls/`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Description**: Retrieves a list of all URLs.

###### Response Example
```json
[
    {
        "id": 1,
        "url": "http://example.com",
        "name": "Example",
        "description": "Example description"
    }
]
```

##### 2. Create a New URL
- **Endpoint Name**: Create
- **Method**: POST
- **URL**: `{{BASE_URL}}/api/v1/urls/`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Body**: Raw (JSON)
```json
{
    "url": "http://example.com",
    "name": "Example",
    "description": "Example description"
}
```
- **Description**: Creates a new shortened URL.

###### Response Example
```json
{
    "id": 1,
    "url": "http://example.com",
    "name": "Example",
    "description": "Example description"
}
```

##### 3. Get URL Details
- **Endpoint Name**: Details
- **Method**: GET
- **URL**: `{{BASE_URL}}/api/v1/urls/{id}`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Description**: Retrieves details of a specific URL by ID.

###### Response Example
```json
{
    "id": 1,
    "url": "http://example.com",
    "name": "Example",
    "description": "Example description"
}
```

##### 4. Update URL
- **Endpoint Name**: Update
- **Method**: PUT
- **URL**: `{{BASE_URL}}/api/v1/urls/{id}`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Body**: Raw (JSON)
```json
{
    "description": "Updated description"
}
```
- **Description**: Updates the details of a specific URL by ID.

###### Response Example
```json
{
    "id": 1,
    "url": "http://example.com",
    "name": "Example",
    "description": "Updated description"
}
```

##### 5. Partially Update URL
- **Endpoint Name**: Update Part
- **Method**: PATCH
- **URL**: `{{BASE_URL}}/api/v1/urls/{id}`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Body**: Raw (JSON)
```json
{
    "name": "Updated Name"
}
```
- **Description**: Partially updates the details of a specific URL by ID.

###### Response Example
```json
{
    "id": 1,
    "url": "http://example.com",
    "name": "Updated Name",
    "description": "Example description"
}
```

##### 6. Delete URL
- **Endpoint Name**: Delete
- **Method**: DELETE
- **URL**: `{{BASE_URL}}/api/v1/urls/{id}`
- **Authorization**: Bearer Token `{{Auth_Token_Global}}`
- **Description**: Deletes a specific URL by ID.

###### Response Example
```json
{
    "message": "URL deleted successfully"
}
```

##### 7. Register
- **Endpoint Name**: Register
- **Method**: POST
- **URL**: `{{BASE_URL}}/api/v1/auth/register`
- **Body**: Raw (JSON)
```json
{
    "username": "user",
    "password": "password"
}
```
- **Description**: Registers a new user.

###### Response Example
```json
{
    "message": "User registered successfully"
}
```

##### 8. Login
- **Endpoint Name**: Login
- **Method**: POST
- **URL**: `{{BASE_URL}}/api/v1/auth/login`
- **Body**: Raw (JSON)
```json
{
    "username": "user",
    "password": "password"
}
```
- **Description**: Logs in an existing user and retrieves a token.

###### Response Example
```json
{
    "token": "your-token-here"
}
```

##### 9. Visit URL
- **Endpoint Name**: Visit
- **Method**: GET
- **URL**: `{{BASE_URL}}/api/v1/visit/{shortened_url}`
- **Description**: Visits the shortened URL and redirects to the original URL.

###### Response Example
```json
{
    "message": "Redirecting to original URL"
}
```

This documentation provides an overview of the URL Shortener API and how to use the endpoints defined in the Postman collection. Ensure to replace placeholder values like `{id}`, `{shortened_url}`, and tokens with actual values when making requests.