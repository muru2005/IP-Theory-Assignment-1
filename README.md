# Online Complaint Registration and Tracking System
**Submitted by:** Murari Sreekumar | **Class:** CSE B | **Reg No:** 3122235001087
## How to Run Instructions

### Prerequisites
1.  **Apache Tomcat** (Tested on v11.0.18)
2.  **MySQL Server**
3.  **Java JDK**

### Setup Steps
1.  **Database Setup**:
    -   Create a database named `complaint_system` (or update `DBUtil.java` with your DB name).
    -   Run the `db.sql` script (or create tables manually as per the documentation).
2.  **Deployment**:
    -   Copy the `ComplaintSystem` folder to your Tomcat `webapps` directory.
    -   Ensure `mysql-connector-j-8.3.0.jar` is in `WEB-INF/lib`.
3.  **Configuration**:
    -   Open `src/com/complaint/DBUtil.java` and verify your MySQL username and password.
4.  **Running**:
    -   Start Tomcat (`bin/startup.bat`).
    -   Access the application at: `http://localhost:8080/ComplaintSystem/`.

### File Description

#### Frontend (View)
-   `index.html`: Landing page linking to Registration and Tracking.
-   `register.html`: Complaint registration form.
-   `track.html`: Form to enter Complaint ID for tracking.
-   `admin_login.html`: Admin login page.
-   `css/style.css`: Stylesheet for all pages.
-   `js/validation.js`: JavaScript for form validation.

#### Backend (Controller)
-   `src/com/complaint/RegisterComplaintServlet.java`: Handles new complaint submission and duplicate checks.
-   `src/com/complaint/TrackServlet.java`: Retrieves complaint status and remarks.
-   `src/com/complaint/AdminLoginServlet.java`: Authenticates admin users.
-   `src/com/complaint/AdminDashboardServlet.java`: Displays all complaints for admin.
-   `src/com/complaint/UpdateComplaintServlet.java`: Updates complaint status and remarks.
-   `src/com/complaint/DBUtil.java`: Database connection utility.

#### Configuration
-   `WEB-INF/web.xml`: Deployment descriptor mapping URLs to Servlets.

### Project Structure
```
ComplaintSystem/
├── src/com/complaint/
│   ├── AdminDashboardServlet.java
│   ├── AdminLoginServlet.java
│   ├── AdminLogoutServlet.java
│   ├── DBUtil.java
│   ├── RegisterComplaintServlet.java
│   ├── TrackServlet.java
│   └── UpdateComplaintServlet.java
├── css/
│   └── style.css
├── js/
│   └── validation.js
├── WEB-INF/
│   └── web.xml
├── index.html
├── register.html
├── track.html
├── admin_login.html
└── README.md
```
