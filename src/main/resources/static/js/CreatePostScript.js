//Validación de datos ingresados
const formPost = document.getElementById("form-create-post");

formPost.addEventListener("submit", (event) => {
    const title = document.getElementById("tittle").value.trim();
    const content = document.getElementById("content").value.trim();

    if (title === "" || content === "") {
        event.preventDefault();
        
        alert("Los campos no pueden estar vacíos ni contener solo espacios en blanco.");
    }
});
