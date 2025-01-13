//Validación de datos ingresados
const formPost = document.getElementById("form-post");

formPost.addEventListener("submit", (event) => {
    
    const titleInput = document.getElementById("tittle") || document.getElementById("title");
    const title = titleInput.value.trim();
    const content = document.getElementById("content").value.trim();

    if (title === "" || content === "") {
        event.preventDefault();
        const div = document.getElementById("header-post");
        const errorMessage = document.createElement("span");
        errorMessage.textContent = "Los campos son obligatorios";
        errorMessage.style.color = "red";
        errorMessage.style.fontWeight = "bold";
        errorMessage.id = "error-message"; 
        const existingError = document.getElementById("error-message");
        if (!existingError) {
            div.appendChild(errorMessage);
        }
    }
});

const titleInput = document.getElementById("tittle") || document.getElementById("title");
const contentInput = document.getElementById("content");
const inputs = [titleInput, contentInput];
inputs.forEach( input => {
   input.addEventListener("focus", () => {
    const errorMessage = document.getElementById("error-message");
    if (errorMessage) {
        errorMessage.remove();
    }
}); 
});
