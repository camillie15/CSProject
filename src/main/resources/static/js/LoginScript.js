

const formLogin = document.getElementById("login-form");


formLogin.addEventListener('submit', (e) => {
    const email = formLogin.elements['email'].value.trim();
    const password = formLogin.elements['password'].value.trim();
    if (testNull(email, password)) {
        e.preventDefault(); // Detiene el envío del formulario
        console.log("Campos vacíos");
        return; // Salir de la función si hay campos vacíos
    }
});


// Retorna true si algún campo del form está vacío
function testNull(...fields) {
    return fields.some(field => field === "");
}