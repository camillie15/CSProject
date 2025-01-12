

const formLogin = document.getElementById("login-form");
const message = document.querySelector(".message");

formLogin.addEventListener('submit', (e) => {
    const email = formLogin.elements['email'].value.trim();
    const password = formLogin.elements['password'].value.trim();
    if (testNull(email, password)) {
        e.preventDefault(); // Detiene el envío del formulario
        console.log("Campos vacíos");
        message.textContent = "*Campos vacios";
        return; // Salir de la función si hay campos vacíos
    }
});


// Retorna true si algún campo del form está vacío
function testNull(...fields) {
    return fields.some(field => field === "");
}