//Metodo para comprobar que ningun campo se ingrese vacio y con su respectivo formato 
const formRegister = document.getElementById("user-register");
const message = document.querySelector(".message");

formRegister.addEventListener('submit', (e) => {
    const name = formRegister.elements['name'].value.trim();
    const last_name = formRegister.elements['last_name'].value.trim();
    const username = formRegister.elements['username'].value.trim();
    const email = formRegister.elements['email'].value.trim();
    const password = formRegister.elements['password'].value.trim();
    const pass = formRegister.elements['pass'].value.trim();

    if (testNull(name, last_name, username, email, password, pass)) {
        e.preventDefault(); // Detiene el envío del formulario
        console.log("Campos vacíos");
        message.textContent = "*Campos vacios";
        return; // Salir de la función si hay campos vacíos
    } else if (comparePass(password, pass)) {
        e.preventDefault(); // Detiene el envío del formulario
        console.log("Contraseñas diferentes");
        message.textContent = "*Las contraseñas no coinciden";
        return; // Salir de la función si hay campos vacíos
    }

});

// Retorna true si algún campo del form está vacío

function testNull(...fields) {
    return fields.some(field => field === "");
}

//Metodo para cancelar el envio del formulario si las contraseñas no son iguales

function comparePass(flag1, flag2) {
    return flag1 !== flag2; //Devuelve true si ambas contraseñas son iguales
}