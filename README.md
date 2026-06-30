# su26-crud-api-demo

A comprehensive RESTful API for managing blog post records, built with Spring Boot, Spring Data JPA, and PostgreSQL.

## Table of Contents

- [What is This Project?](#what-is-this-project)
- [Technology Stack](#technology-stack)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [Project Architecture](#project-architecture)
- [API Endpoints](#api-endpoints)
- [Key Spring Boot Concepts](#key-spring-boot-concepts)
- [Database Schema](#database-schema)

---

# What is This Project?

This is a CRUD (Create, Read, Update, Delete) API that manages blog post records. It demonstrates:

- How to build a REST API with Spring Boot
- How to connect to a PostgreSQL database using JPA
- How to structure a Spring Boot application with layers (Controller, Service, Repository)
- How to handle HTTP requests and responses
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
| **Hibernate**       | Latest  | JPA implementation                     |
| **PostgreSQL**      | Latest  | Relational database                    |
| **Maven**           | Latest  | Build and dependency management        |

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

---

## Installation & Setup

### Prerequisites

Before you begin, ensure you have installed:

1. **Java 25 JDK**
   - Download from [Oracle Java](https://www.oracle.com/java/technologies/downloads/) or use a package manager
   - Verify installation: `java -version`

2. **Neon.tech PostgreSQL Database** (Cloud-based, Serverless)
   - This project uses [Neon.tech](https://neon.tech), a serverless PostgreSQL database in the cloud
   - You don't need to install PostgreSQL locally
   - Sign up for a free account at [Neon.tech](https://neon.tech)
   - You only need an internet connection to connect to the database

3. **Git** (optional, for cloning the project)
   - Download from [Git Official Site](https://git-scm.com/)

### Setup Instructions

1. **Clone or Download the Project**

   ```bash
   git clone <repository-url>
   cd su26-crud-api-demo
   ```

2. **Database Configuration (Neon.tech Serverless PostgreSQL)**

   #### Step 1: Get Your Neon.tech Connection String
   1. Navigate to [Neon.tech](https://neon.tech)
   2. Sign in to your account
   3. In your project dashboard, find your connection string
   4. It will look like: `jdbc:postgresql://project-name.c-2.us-east-1.aws.neon.tech/neondb?user:5432/dbname?user==neondb_owner&password=your_password_here`

   #### Step 2: Stop Tracking `application.properties` Locally

   To prevent accidentally committing your database credentials to Git, use `git skip-worktree` to exclude your local copy:

   ```bash
   git update-index --skip-worktree src/main/resources/application.properties
   ```

   This tells Git to ignore any changes you make to this file locally. You can now safely edit the file without worrying about committing sensitive data.

   #### Step 3: Update Your Connection String

   Edit `src/main/resources/application.properties` and add your Neon.tech PostgreSQL connection string:

   ```properties
   spring.application.name=crud-api
   spring.datasource.url=YOUR NEON CONNECTION STRING
   spring.jpa.hibernate.ddl-auto=update

   #Log out sql queries
   logging.level.org.hibernate.SQL=DEBUG
   logging.level.org.hibernate.orm.jdbc.bind=TRACE
   ```

   Replace with your actual Neon.tech credentials (e.g., `some-cool-projectName-pooler.c-7.us-east-1.aws.neon.tech//neondb?user=neondb_owner&password=your_password_here&sslmode=require&channelBinding=require`).

   #### To Resume Tracking the File

   If you need to revert and track the file again:

   ```bash
   git update-index --no-skip-worktree src/main/resources/application.properties
   ```

   **Important Note**: This approach (using `git skip-worktree`) keeps credentials safe locally while the file can be tracked in Git. However, in production environments, database credentials should be managed using environment variables or cloud-based secret management services like AWS Secrets Manager or Azure Key Vault.

## Running the Application

### Using Maven Wrapper

**On Windows CMD**:

```cmd
mvnw.cmd spring-boot:run
```

**On Mac/Linux (bash, powershell, zsh**):

```bash
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**

You should see output like:

```
Started CrudApiApplication in 4.532 seconds
```

### Using VS Code GUI

1. **Open the Project**: Open the project folder in VS Code
2. **Install Extension**: Install the "Extension Pack for Java" (by Microsoft) if not already installed
3. **Run the Application**:
   - Go to the Explorer view (left sidebar)
   - Navigate to `src > main > java > com > csc340 > crud_api > CrudApiApplication.java`
   - Right-click on `CrudApiApplication.java`
   - Select **"Run Java"** or click the ▶️ **Run** button that appears above the class definition
4. **View Output**: The terminal will show the Spring Boot startup messages and confirm the application is running

### Using IntelliJ IDEA GUI

1. **Open the Project**: Open the project folder in IntelliJ IDEA (it will recognize it as a Maven project)
2. **Configure JDK**:
   - Go to **File → Project Structure → Project**
   - Set the Project SDK to Java 25
3. **Run the Application**:
   - Navigate to `src > main > java > com > csc340 > crud_api > CrudApiApplication.java` in the Project Explorer
   - Right-click on `CrudApiApplication.java`
   - Select **"Run 'CrudApiApplication.main()'"** or click the ▶️ **Run** button next to the class name
4. **View Output**: The Run window at the bottom will show Spring Boot startup messages and confirm the application is running

### Stopping the Application

Press `Ctrl+C` in your terminal to stop the running application. If using IDE GUI, click the ⏹️ **Stop** button in the Run/Debug toolbar.

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
    └── PostApiController.java       # REST controller handling HTTP requests

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
| (PostApiController.java)            │
│ - Handles HTTP requests/responses   │
│ - Maps URLs to methods(endpoints)   │
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

````

## API Endpoints

All endpoints use the base URL: `http://localhost:8080/api/posts`

### 1. Get All Posts

```http
GET /api/posts
````

**Response:**

```json
[
  {
    "id": 1,
    "title": "First Post",
    "author": "John Doe",
    "content": "This is the content of the first post."
  },
  {
    "id": 2,
    "title": "Second Post",
    "author": "Jane Smith",
    "content": "This is the content of the second post."
  }
]
```

### 2. Get Post by ID

```http
GET /api/posts/{id}
```

**Response:**

```json
{
  "id": 1,
  "title": "First Post",
  "author": "John Doe",
  "content": "This is the content of the first post."
}
```

### 3. Create a New Post

```http
POST /api/posts
request body:
{
  "title": "New Post",
  "author": "Alice Johnson",
  "content": "This is the content of the new post."
}
```

**Response:**

```json
{
  "id": 3,
  "title": "New Post",
  "author": "Alice Johnson",
  "content": "This is the content of the new post."
}
```

### 4. Update an Existing Post

```http
PUT /api/posts/{id}
request body:
{
  "title": "Updated Post Title",
  "author": "John Doe",
  "content": "This is the updated content of the post."
}
```

**Response:**

```json
{
  "id": 1,
  "title": "Updated Post Title",
  "author": "John Doe",
  "content": "This is the updated content of the post."
}
```

### 5. Delete a Post

```http
DELETE /api/posts/{id}
```

**Response:** <Empty>

### 6. Search Posts by Title or Content

```http
GET /api/posts/search?query={searchTerm}
```

**Response:**

```json
[
  {
    "id": 1,
    "title": "First Post",
    "author": "John Doe",
    "content": "This is the content of the first post."
  }
]
```

### 7. Search Posts by Author

```http
GET /api/posts/search?author={authorName}
```

**Response:**

```json
[
  {
    "id": 2,
    "title": "Second Post",
    "author": "Jane Smith",
    "content": "This is the content of the second post."
  }
]
```

## Key Spring Boot Concepts

### What is Spring Boot?

Spring Boot is a framework that simplifies building production-ready Spring applications. It provides:

- Auto-configuration of Spring application based on jar dependencies
- Embedded web server (Tomcat) - no need to deploy WAR files
- Convention over configuration - sensible defaults
- Easy integration with databases and other services

### @RestController and @RequestMapping

```java
@RestController
@RequestMapping("/api/posts")
public class PostApiController {
    // Controller methods here
}
```

- `@RestController`: Indicates that this class is a REST controller, capable of handling HTTP requests and returning JSON responses.
- `@RequestMapping("/api/posts")`: All endpoints in this class start with `/api/posts`.

### HTTP Mapping Annotations

- `@GetMapping`: Handles GET requests (retrieve data)
- `@PostMapping`: Handles POST requests (create data)
- `@PutMapping`: Handles PUT requests (update data)
- `@DeleteMapping`: Handles DELETE requests (delete data)
- `ResponseEntity`: Represents the entire HTTP response, including status code, headers, and body.
- `@RequestParam`: Binds query parameters from the URL to method parameters.
- `@RequestBody`: Binds the request body (JSON) to a Java object.

### @Service and Dependency Injection

```java
@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
```

- `@Service`: Indicates that this class contains business logic and is a service component.
- Dependency Injection: Spring automatically injects the `PostRepository` into the `PostService` constructor, allowing us to use it without manually instantiating it.

### Spring Data JPA Repository

```java
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
    List<Post> findByAuthorIgnoreCase(String author);
}
```

- `JpaRepository<Post, Long>`: Provides CRUD operations and query methods for the `Post` entity.
- Spring automatically generates implementations for these methods based on their names.
- `findByAuthorIgnoreCase`: Genarates a query like

```sql
SELECT * FROM posts WHERE UPPER(author) = UPPER(?)
```

### @Entity and JPA Annotations

```java
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;
}
```

- `@Entity`: Marks this class as a JPA entity, representing a table in the database.
- `@Id`: Specifies the primary key of the entity.
- `@GeneratedValue`: Configures how the primary key is generated (e.g., auto-increment).
- `@Column`: Specifies column properties, such as nullability and data type.

---

## Database Schema

The application uses a single table to store student data:

### POSTS Table

| Column    | Type         | Constraints | Description                     |
| --------- | ------------ | ----------- | ------------------------------- |
| `id`      | BIGINT       | PRIMARY KEY | Unique identifier for each post |
| `title`   | VARCHAR(255) | NOT NULL    | Title of the blog post          |
| `author`  | VARCHAR(255) | NOT NULL    | Author of the blog post         |
| `content` | TEXT         |             | Content of the blog post        |

**Note**: This schema is automatically created by Hibernate based on the entity class when `spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`.

---

## Testing the API

### Using Postman/Echo API/Bruno (GUI)

1. Create a new request
2. Select HTTP method (GET, POST, PUT, DELETE)
3. Enter the URL (e.g., `http://localhost:8080/api/posts`)
4. If POST/PUT, go to "Body" tab → select "raw" and "JSON"
5. Enter JSON data and click "Send"

---

## Common Issues and Solutions

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
