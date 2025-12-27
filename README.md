# 📧 AI Email Assistant  
### Spring Boot • Google Gemini API • Gmail Integration

An **AI-powered Email Assistant** that generates **professional email replies directly inside Gmail**.  
Built using **Spring Boot** for the backend and **Google Gemini API** for intelligent text generation, with a **Chrome content script** to inject a *Generate* button into Gmail.

---

## ✨ Key Highlights (Recruiter-Friendly)

✔ Real-world AI integration with Spring Boot  
✔ Gmail UI automation using JavaScript  
✔ Context-aware email reply generation  
✔ Clean REST API design  
✔ Practical use-case (email productivity tool)

---

## 🚀 Features

- 📩 Generate **professional email replies** using AI  
- 🧠 Context-aware responses based on received email  
- 🎯 Tone support (currently: `professional`)  
- 🧩 Gmail integration via injected **Generate** button  
- 🔌 REST API accessible via Postman & UI  

---

## 🖼️ Screenshots


### Received Mail
<img width="1596" height="490" alt="image" src="https://github.com/user-attachments/assets/c1dbcde3-5a3b-4c81-a3b3-c149cac47556" />


### Gmail Generate Button
<img width="1481" height="358" alt="image" src="https://github.com/user-attachments/assets/aec65e43-545c-45ad-b233-4a874f1868e6" />


### AI Generated Reply in Gmail
<img width="1405" height="533" alt="image" src="https://github.com/user-attachments/assets/d995238f-3c2b-4ba6-918f-4e8397b71437" />


---

## 🏗 Tech Stack

### Backend
- Java  
- Spring Boot  
- Spring WebFlux (`WebClient`)  
- Google Gemini API  

### Frontend
- JavaScript  
- Chrome Extension (Content Script)  
- Gmail DOM Manipulation  

---

## 📂 Project Structure
Email-Assistance-JavaCode/
├── controller/
│ └── EmailGeneratorController.java
├── service/
│ └── EmailGeneratorService.java
├── pojo/
│ └── EmailRequest.java
├── resources/
│ └── application.properties
└── MailApplication.java

Email-writer.ext/
├── content.js
└── manifest.json
