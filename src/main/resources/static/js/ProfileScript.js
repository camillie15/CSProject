const btnAction = document.getElementById("btn-action");
const inputsConfig = document.querySelectorAll('#user-update input[type="text"]');
const btnUpdate = document.getElementById("btn-update");
const btnCancel = document.getElementById("btn-cancel");


btnAction.addEventListener("click", () => {
    console.log("change");
    inputsConfig.forEach(x => x.removeAttribute("disabled"));
    btnAction.setAttribute("hidden", true);
    btnUpdate.removeAttribute("hidden");
    btnCancel.removeAttribute("hidden")
});


btnCancel.addEventListener("click", () => {
    inputsConfig.forEach(x => x.setAttribute("disabled", true));
    btnAction.removeAttribute("hidden");
    btnUpdate.setAttribute("hidden", true);
    btnCancel.setAttribute("hidden", true)
});


//Metodo para comprobar que ningun campo se ingrese vacio y con su respectivo formato 
const formUpdate = document.getElementById("user-update");

formUpdate.addEventListener('submit', (e) => {
    const name = formUpdate.elements['name'].value.trim();
    const last_name = formUpdate.elements['last_name'].value.trim();
    const username = formUpdate.elements['username'].value.trim();
    const email = formUpdate.elements['email'].value.trim();
    const password = formUpdate.elements['password'].value.trim();

    if (testNull(name, last_name, username, email, password)) {
        e.preventDefault(); // Detiene el envío del formulario
        console.log("Empty fields");
        return; // Salir de la función si hay campos vacíos
    }
});


// Retorna true si algún campo del form está vacío

function testNull(...fields) {
    return fields.some(field => field === "");
}
//Validación datos ingresados en el editar post
const allEditForms = document.querySelectorAll('.form-edit');

allEditForms.forEach(form => {
    form.addEventListener('submit', (event) => {
        const title = form.elements['title'].value.trim();
        const content = form.elements['content'].value.trim();
        if (title === "" || content === "") {
            event.preventDefault();
            alert("El post no debe estar vacío ni contener solo espacios en blanco.");
        }
    });
});

//Función para visualización de formulario para editar comentario
function editPost(event) {
    const a = event.target;
    const postId = a.getAttribute("data-post-id");

    const allForms = document.querySelectorAll('.show');
    allForms.forEach(form => {
        form.classList.remove('show');
        form.classList.add('form-edit');
    });

    const formEdit = document.getElementById(`form-edit-${postId}`);
    if (formEdit) {
        formEdit.classList.remove('form-edit');
        formEdit.classList.add('show');
    }
}

//Función para cancelar y cerrar formulario de editar comentario
function cancelEdit(event) {
    const btnCancelar = event.target;

    const postId = btnCancelar.getAttribute("data-post-id");

    const form = document.getElementById(`form-edit-${postId}`);
    const inputTitle = form.querySelector('input[name="title"]');
    const inputContent = form.querySelector('input[name="content"]');
    
    if (form) {
        form.classList.remove('show');
        form.classList.add('form-edit');
        inputTitle.value = inputTitle.defaultValue;
        inputContent.value = inputContent.defaultValue;
    }
}
