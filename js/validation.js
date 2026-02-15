function validateForm(){
    let name=document.forms["complaintForm"]["name"].value.trim();
    let email=document.forms["complaintForm"]["email"].value.trim();
    let category=document.forms["complaintForm"]["category"].value.trim();
    let description=document.forms["complaintForm"]["description"].value.trim();
    if(name===""){
        alert("Name is required");
        return false;
    }
    if(name.length<3){
        alert("name shld eb atleast 3 characters long");
        return false;
    }
    if(email===""){
        alert("email is requried");
        return false;
    }
    let emailPattern=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!emailPattern.test(email)){
        alert("invalid email address");
        return false;
    }
    if(category===""){
       alert("Category is required");
       return false;
    }
    if(category.length<3){
        alert("category shld be alteast 3 characters");
        return false;
    }
    if(description===""){
        alert("Description shld be alteast 10 characters long");
        return false;
    }
    if(description.length<10){
        alert("description shld atleast be 10 characters long.");
        return false;
    }
    return true;

}