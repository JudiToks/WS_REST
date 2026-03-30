# Blog REST API - Documentation Swagger

## Base URL
```
http://localhost:1820/api/v1
```

## Authentication
Tous les endpoints protégés nécessitent un token JWT dans le header:
```
Authorization: Bearer <token>
```

---

## Endpoints

### 1. Authentication

#### Register
```
POST /auth/register
```
**Body:**
```json
{
  "username": "jean",
  "email": "jean@example.com",
  "password": "password123"
}
```
**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "jean",
  "message": "Registered successfully"
}
```

#### Login
```
POST /auth/login
```
**Body:**
```json
{
  "username": "jean",
  "password": "password123"
}
```
**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "jean",
  "message": "Login successful"
}
```

---

### 2. Posts

#### Create Post
```
POST /posts
Authorization: Bearer <token>
```
**Body:**
```json
{
  "title": "My First Post",
  "content": "This is the content"
}
```
**Response (200):**
```json
{
  "id": 1,
  "title": "My First Post",
  "content": "This is the content",
  "author": "jean",
  "createdAt": "2026-03-30T10:00:00"
}
```

#### List Posts
```
GET /posts
```
**Response (200):**
```json
[
  {
    "id": 1,
    "title": "My First Post",
    "content": "This is the content",
    "author": "jean",
    "published": true,
    "createdAt": "2026-03-30T10:00:00"
  }
]
```

#### Get Post
```
GET /posts/{id}
```
**Response (200):**
```json
{
  "id": 1,
  "title": "My First Post",
  "content": "This is the content",
  "author": "jean",
  "published": true,
  "createdAt": "2026-03-30T10:00:00"
}
```

#### Update Post
```
PUT /posts/{id}
Authorization: Bearer <token>
```
**Body:**
```json
{
  "title": "Updated Title",
  "content": "Updated content"
}
```
**Response (200):** Same as Get Post

#### Delete Post
```
DELETE /posts/{id}
Authorization: Bearer <token>
```
**Response (200):** Success

---

### 3. Comments

#### Create Comment
```
POST /comments
Authorization: Bearer <token>
```
**Body:**
```json
{
  "postId": 1,
  "content": "Great post!"
}
```
**Response (200):**
```json
{
  "id": 1,
  "content": "Great post!",
  "author": "jane",
  "postId": 1,
  "createdAt": "2026-03-30T10:05:00"
}
```

#### List Comments
```
GET /comments?postId=1
```
**Response (200):**
```json
[
  {
    "id": 1,
    "content": "Great post!",
    "author": "jane",
    "postId": 1,
    "createdAt": "2026-03-30T10:05:00"
  }
]
```

#### Delete Comment
```
DELETE /comments/{id}
Authorization: Bearer <token>
```
**Response (200):** Success

---

### 4. Ratings

#### Rate Post
```
POST /ratings
Authorization: Bearer <token>
```
**Body:**
```json
{
  "postId": 1,
  "score": 5,
  "comment": "Excellent post!"
}
```
**Response (200):**
```json
{
  "id": 1,
  "postId": 1,
  "score": 5,
  "comment": "Excellent post!",
  "author": "jean",
  "createdAt": "2026-03-30T10:10:00"
}
```

#### Get Post Ratings
```
GET /ratings?postId=1
```
**Response (200):**
```json
[
  {
    "id": 1,
    "postId": 1,
    "score": 5,
    "comment": "Excellent post!",
    "author": "jean",
    "createdAt": "2026-03-30T10:10:00"
  }
]
```

#### Delete Rating
```
DELETE /ratings/{id}
Authorization: Bearer <token>
```
**Response (200):** Success

---

### 5. Categories

#### Create Category
```
POST /categories
Authorization: Bearer <token> (Admin only)
```
**Body:**
```json
{
  "name": "Technology",
  "description": "Tech related posts"
}
```

#### List Categories
```
GET /categories
```

#### Get Category
```
GET /categories/{id}
```

#### Update Category
```
PUT /categories/{id}
Authorization: Bearer <token> (Admin only)
```

#### Delete Category
```
DELETE /categories/{id}
Authorization: Bearer <token> (Admin only)
```

---

### 6. Tags

#### Create Tag
```
POST /tags
Authorization: Bearer <token> (Admin only)
```

#### List Tags
```
GET /tags
```

#### Get Tag
```
GET /tags/{id}
```

#### Update Tag
```
PUT /tags/{id}
Authorization: Bearer <token> (Admin only)
```

#### Delete Tag
```
DELETE /tags/{id}
Authorization: Bearer <token> (Admin only)
```

---

## Status Codes

| Code | Meaning |
|------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Server Error |

---

## Error Response

```json
{
  "error": "Error message",
  "timestamp": "2026-03-30T10:00:00",
  "status": 400
}
```

---

## Examples avec cURL

### Register
```bash
curl -X POST http://localhost:1820/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"jean","email":"jean@example.com","password":"pass123"}'
```

### Login
```bash
curl -X POST http://localhost:1820/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"jean","password":"pass123"}'
```

### Create Post
```bash
TOKEN="eyJhbGciOiJIUzUxMiJ9..."

curl -X POST http://localhost:1820/api/v1/posts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"title":"My Post","content":"Content here"}'
```

### List Posts
```bash
curl http://localhost:1820/api/v1/posts
```

---

## Swagger UI

Accédez à l'interface Swagger interactive:
```
http://localhost:1820/swagger-ui.html
```

JSON Schema:
```
http://localhost:1820/api-docs
```
