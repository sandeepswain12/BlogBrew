# 📝 Blogbrew - A Full-Stack Blog Application

Blogbrew is a full-stack blog platform built with **Spring Boot**, **Thymeleaf**, **TailwindCSS**, and deployed on **AWS**.  
It allows users to register, sign in, create, edit, and publish blog posts. Visitors can browse and read public blog content.

---

## 🚀 Features

- ✅ User authentication with email & password
- ✅ Create, edit, delete blogs (only for authors)
- ✅ Public listing and detail view for blogs
- ✅ Pagination for blog list
- ✅ Responsive UI (Mobile & Desktop)
- ✅ Secure access with Spring Security
- ✅ Hosted on AWS (Elastic Beanstalk)

---

## 🖥️ Tech Stack

| Frontend        | Backend         | Deployment |
|-----------------|------------------|------------|
| HTML + TailwindCSS + Thymeleaf | Spring Boot (Java), Spring Security, Spring Data JPA | AWS (Elastic Beanstalk) |
|                 | MySQL (RDS)       |             |

---

## 🛠️ Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL (or use your own DB)
- AWS account (for deployment)

### Clone the project

```bash
git clone https://github.com/sandeepswain12/BlogBrew.git
cd BlogBrew
```


## ⚙️ Configure the application

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<host>:3306/blogbrew
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ Run the project

```bash
mvn clean install
mvn spring-boot:run
```

Access the app at:  
👉 [http://localhost:8085](http://localhost:8085)



🌐 Live Demo
✅ Hosted on AWS:
🔗 www.blogbrew.xyz

📁 Folder Structure

```bash
BlogBrew/
├── src/
│   ├── main/
│   │   ├── java/com/blogbrew/...
│   │   └── resources/
│   │       ├── templates/
│   │       ├── static/
│   │       └── application.properties
├── pom.xml
└── README.md
```


🧠 Author
Made with ❤️ by Sandeep Kumar Swain

📧 Email: sandeepswain027@gmail.com

🔗 LinkedIn: sandeep-kumar-swain-778b40237

