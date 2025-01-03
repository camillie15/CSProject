//Validación de datos ingresados
const formComment = document.getElementById("form-add-comment");

formComment.addEventListener("submit", (event) => {
    const content = document.getElementById("content").value.trim();

    if (content === "") {
        event.preventDefault();
        
        alert("El comentario no debe estar vacio ni contener solo espacios en blanco.");
    }
});
