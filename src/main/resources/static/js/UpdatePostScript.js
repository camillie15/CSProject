//Validación datos ingresados en el editar post
function validateForm() {
    title = document.getElementById('title').value.trim();
    content = document.getElementById('content').value.trim();

    if (title === "" || content === "") {
        event.preventDefault();
        alert("El post no debe estar vacío ni contener solo espacios en blanco.");
    }
}
