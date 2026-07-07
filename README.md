# su26-crud-api-demo

A comprehensive ~~RESTful API~~ MVC application for managing blog post records, built with Spring Boot, Spring Data JPA, and PostgreSQL.
This project demonstrates fundamental concepts for building both REST APIs and web interfaces with Spring Boot.

---

# What is This Project?

This is a CRUD (Create, Read, Update, Delete) MVC Application that manages blog post records. It demonstrates:

- How to build a REST API with Spring Boot
- How to connect to a PostgreSQL database using JPA
- How to structure a Spring Boot application with layers (Controller, Service, Repository)
- How to handle HTTP requests and responses
- How to render web pages with FreeMarker templates
- How to perform database operations

**CRUD:**

- **Create**: Add new blog posts
- **Read**: Retrieve existing blog posts
- **Update**: Modify existing blog posts
- **Delete**: Remove blog posts

---

# Technology Stack

| Technology          | Version | Purpose                                |
| ------------------- | ------- | -------------------------------------- |
| **Java**            | 25      | Programming language                   |
| **Spring Boot**     | 4.1.0   | Framework for building the application |
| **Spring Data JPA** | Latest  | ORM layer for database access          |
| **FreeMarker**      | Latest  | Template engine for rendering views    |
| **Hibernate**       | Latest  | JPA implementation                     |
| **PostgreSQL**      | Latest  | Relational database                    |
| **Maven**           | Latest  | Build and dependency management        |
| **Lombok**          | Latest  | Reduces boilerplate code               |
| **docker**          | Latest  | Containerization                       |

### Java - [Spring ORM with JPA and Hibernate](https://medium.com/@burakkocakeu/jpa-hibernate-and-spring-data-jpa-efa71feb82ac)

- We are using ORM (Object-Relational Mapping) to deal with databases. This is a technique that allows us to interact with a relational database using object-oriented programming principles.
- JPA (Jakarta Persistence, formerly Java Persistence API) is a specification that defines ORM standards in Java. It provides an abstraction layer for ORM frameworks to make concrete implementations.
- Hibernate: Hibernate is a popular ORM framework that implements JPA. It simplifies database operations by mapping Java objects to database tables and handling queries efficiently.
- Spring ORM allows seamless integration of Hibernate and JPA, making database interactions more manageable and reducing boilerplate code.

### Key Dependencies

**spring-boot-starter-data-jpa**: Provides Spring Data JPA for simplified database access through repositories and automatic query generation.

**spring-boot-starter-webmvc**: Provides Spring Web MVC for building REST APIs with annotations like `@RestController`, `@GetMapping`, etc.

**postgresql**: JDBC driver to connect to PostgreSQL database.

**lombok**: Reduces boilerplate code by generating getters, setters, constructors, and other methods at compile time.

**freemarker**: Template engine for rendering dynamic HTML pages.

---

## Installation & Setup

### Prerequisites

Before you begin, ensure you:

1. **Neon.tech PostgreSQL Database** (Cloud-based, Serverless)
   - This project uses [Neon.tech](https://neon.tech), a serverless PostgreSQL database in the cloud
   - You don't need to install PostgreSQL locally
   - Sign up for a free account at [Neon.tech](https://neon.tech)
   - You only need an internet connection to connect to the database

2. **GitHub** (for forking the project)
   - Sign up for a free account at [GitHub](https://github.com/)

3. **Render** (for deployment)
   - Sign up for a free account at [Render](https://render.com/)
   - Render is a cloud platform that allows you to deploy web applications easily

### Setup Instructions

1. **Create a Fork of the Repository**
   1. Go to the GitHub repository: [su26-crud-api-demo](https://github.com/su26/su26-crud-api-demo)
   2. Click the **Fork** button in the top-right corner to create your own copy of the repository
   3. Copy all branches, not just the main branch.

2. **Database Configuration (Neon.tech Serverless PostgreSQL)**
   1. Navigate to [Neon.tech](https://neon.tech)
   2. Sign in to your account
   3. In your project dashboard, find your connection string
   4. It will look like: `jdbc:postgresql://project-name.c-2.us-east-1.aws.neon.tech/neondb?user:5432/dbname?user==neondb_owner&password=your_password_here`
   5. Copy this connection string for later use in environent variables.

3. **Deploying on Render**

   #### Step 1: Create a New Web Service on Render
   1. Go to [Render](https://render.com/)
   2. Sign in to your account
   3. Click **New** → **Web Service**
   4. Connect your GitHub account and select the forked repository, or paste the repository URL.
   5. Under **Language**, select **docker**.
   6. Choose the branch you want to deploy (e.g., `crud-api-for-docker`)
   7. Set the dockerfile path to `./dockerfile`.
   8. Be sure to select the free plan for deployment.
   9. Under **Environment Variables**, add the following variable:
      - Key: `SPRING_DATASOURCE_URL`
      - Value: Your Neon.tech connection string (from step 2)
   10. Click **Create Web Service** to deploy your application.

   #### Step 2: Access Your Deployed Application
   - Once the deployment is complete, Render will provide you with a URL for your application.
   - You can access the API endpoints using this URL, e.g., `https://your-app-name.onrender.com/api/posts`.

---

## Project Architecture

### Folder Structure

```
src/main/java/com/csc340/crud_api/
├── CrudApiApplication.java          # Entry point of the application
├── posts/
│   ├── Post.java                    # Entity/model class representing a blog post
    ├── PostRepository.java          # Database access layer using Spring Data JPA
    ├── PostService.java             # Service layer for business logic
    ├── PostApiController.java       # REST controller handling HTTP requests
    ├── PostUiController.java        # Controller for rendering web pages with FreeMarker templates.
    └── AppController.java             # Main controller for handling the root endpoint

src/main/resources/
└── application.properties           # Configuration file
```

### Architectural Pattern: **Layered Architecture**

This project follows a layered architecture pattern, separating concerns into distinct layers:

```
┌─────────────────────────────────────┐
│   HTTP Client (REST Client, Browser)│
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│ Controller Layer                    │
| (PostApiController.java    &        |
|   PostUiController.java)            │
│ - Handles HTTP requests/responses   │
│ - API: Returns JSON responses       │
| - UI: Renders views using templates │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│ Service Layer                       │
| (PostService.java)                  │
│ - Contains business logic           │
| - Processes data from repositories  │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│ Repository Layer                    │
| (PostRepository.java)               │
| - Interacts with the database(JPA)  |
| - Performs CRUD operations          │
└────────────────┬────────────────────┘
                 │
┌────────────────▼────────────────────┐
│ Database                            │
│ (PostgreSQL)                        │
└─────────────────────────────────────┘
```

## API Endpoints

- `GET /api/posts`: Get all blog posts
- `GET /api/ posts`: Render the form to create a new post
- `POST /api/posts`: Create a new post
- `GET /api/posts/{id}`: Get a specific blog post
- `PUT /api/posts/{id}`: Update an existing post
- `DELETE /api/posts/{id}`: Delete a blog post

## Web UI Routes

- `GET /posts`: View all blog posts
- `GET /posts/new`: Render the form to create a new post
- `POST /posts`: Submit the form to create a new post
- `GET /posts/{id}`: View a specific blog post
- `POST /posts/update/{id}`: Submit the form to update an existing post
- `GET /posts/delete/{id}`: Delete a blog post

---

### MVC (Model-View-Controller) Pattern

Spring MVC is a web framework that follows the MVC architectural pattern:

- **Model**: Represents the data (Student entity, service responses)
- **View**: The presentation layer (FreeMarker templates that render HTML)
- **Controller**: Handles user requests, processes them, and returns appropriate responses

```java
@Controller // Handles web requests and returns views
@GetMapping("/posts")
public String getAllPosts(Model model) {
  model.addAttribute("posts", postService.getAllPosts());
  return "posts";
}

@RestController// Handles API requests and returns JSON objects
  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    if (posts.isEmpty()) {
      return ResponseEntity.ok(Collections.emptyList());
    }
    return ResponseEntity.ok(posts);
  }
```

## Common Issues and Solutions

### Issue: Whitelabel Error Page instead of a view

**Solution**: Check that the name of the view being returned is spelled correctly.

### Issue: Port 8080 is already in use

**Solution**: Change the port in `application.properties`:

```properties
server.port=8081
```

The access the API at `http://localhost:8081/api/posts`

### Issue: "Connection refused" when accessing database

**Solution**:

- Ensure you have **internet access** to connect to Neon.tech (the database is cloud-based and always running)
- Verify your connection string is correct in `application.properties`
- Check that your username and password from Neon.tech are correct
- Make sure the host/endpoint is reachable (not blocked by firewall)

### Issue: Getting 404 errors

**Solution**:

- Verify the endpoint URL is correct
- Make sure the application is running (use `mvnw.cmd spring-boot:run` on Windows or `./mvnw spring-boot:run` on Mac/Linux)
- Check the base path is `/api/posts` for all endpoints

### Issue: JSON parsing errors in POST/PUT requests

**Solution**:

- Ensure `Content-Type: application/json` header is set
- Verify JSON syntax is valid (use online JSON validator)
- Check all required fields are included (name and email are required)

---

## Additional Resources

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [REST API Best Practices](https://restfulapi.net/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
