@echo off
if not exist "WEB-INF\classes" mkdir "WEB-INF\classes"
javac -cp ".;E:\apache-tomcat-11.0.18\lib\servlet-api.jar" -d WEB-INF/classes src/com/complaint/*.java
echo Compilation complete.
pause
