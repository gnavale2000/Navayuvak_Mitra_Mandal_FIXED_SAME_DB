# FIXED Navayuvak Mitra Mandal Project
This version uses the SAME existing MySQL database and SAME four tables:
members, collections, expenses, mandal_info.
No database update or new SQL is required.

Important: change only MySQL password in:
src/main/resources/application.properties

If your password is root, leave it as root.

Run in Eclipse/STS:
1. Import as Existing Maven Project.
2. Right click project -> Maven -> Update Project.
3. Run MandalApplication.java.
4. Open http://localhost:8080/

Or command line:
mvn clean
mvn spring-boot:run
