# 🏏 Cricket Info Web Application

A web application built using **Spring Boot**, **Maven**, and **MySQL** with a simple **HTML/JavaScript/CSS** frontend.  
This project helps manage and display **cricket match scores**, **player statuses**, and **match details** in an interactive and organized way.

---

## 🚀 Features

- ➕ Add new matches with teams and scores  
- 👀 View detailed match information  
- 📊 Display live-style scoreboards with player status  
- 🧩 RESTful APIs for match data management  
- 💾 Database integration using MySQL  

---

## 🧠 Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend | Spring Boot (Java) |
| Build Tool | Maven |
| Database | MySQL |
| Frontend | HTML, CSS, JavaScript |
| IDE | Eclipse (recommended) |

---

## ⚙️ Project Setup and Run

### 1. Clone the Repository
```bash
git clone https://github.com/JesinthaChristy/CricInfo.git
cd cricket-info
```

### 2. Configure the Database
- Create a new MySQL database (e.g., `cricket_info_db`)
- Update your `application.properties` file with:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/infocric_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Application
- Open the project in **Eclipse IDE**
- Build using **Maven**
- Run the main Spring Boot class:
  ```
  CricketInfoApplication.java
  ```
- The application will start on:
  ```
  http://localhost:8080
  ```

---

## 🌐 Frontend Usage
- Open the `index.html` file in your browser.
- The frontend connects to backend APIs (running at `localhost:8080/api/...`) to:
  - Display all matches  
  - Show individual match details  
  - Add new match data  

---

## 📁 Project Structure
```
Cricket_Info/
├── src/main/java/com/matchscore/cricketInfo/
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
├── src/main/resources/
│   ├── application.properties
│   └── static/
      └── images/teams/
      └── index.html/
├── pom.xml
└── README.md
```

---

## 🧩 API Endpoints
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/matches` | Get all matches |
| `GET` | `/api/matches/{id}` | Get match by ID |
| `POST` | `/api/matches` | Add a new match |
| `PUT` | `/api/matches/{id}` | Update match |
| `DELETE` | `/api/matches/{id}` | Delete match |

---

## 📸 Screenshots
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/64f717cc-3605-489c-9f47-e50c506c7c02" />
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/0e983ec8-cf82-4021-b471-6547a7dbda3e" />
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/ae915a38-f464-426d-af98-dcef21fa0df8" />
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/596af7b5-019b-4451-be4d-6805fd8f78b9" />
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/c9d5dd55-b2cc-47c4-b091-7059a7b351f9" />







---

## 💡 Future Enhancements
- Player statistics with detailed analytics  
- Live score update simulation  
- Authentication for admin users  

---

## 🤝 Contributing
Contributions are welcome!  
If you'd like to contribute, fork the repository and submit a pull request.

---

## 📜 License
This project is licensed under the **MIT License** — feel free to use and modify it.

---

### 👨‍💻 Author
**Jesintha Christy**  
💌 *Developed with ❤️ using Spring Boot & JavaScript*
