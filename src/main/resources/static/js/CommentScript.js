//Validación de datos ingresados
const formComment = document.getElementById("form-add-comment");
const rol = document.getElementById("user-rol-logged").getAttribute("data-user-logged-rol");

if (rol == 1){
    formComment.addEventListener("submit", (event) => {
    const content = document.getElementById("content").value.trim();
    if (content === "") {
        event.preventDefault();
        const div = document.getElementById("header-comment");
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
}


//Función para interaccciones con los botones de editar y eliminar
function commentOptions() {
    event.stopPropagation();

    const editButtons = document.querySelectorAll('.btn-update');
    const deleteButtons = document.querySelectorAll('.btn-delete');
    const userLoggedId = document.getElementById("user-id-logged").getAttribute("data-user-logged-id");

    editButtons.forEach(editButton => {
        const commentUserId = editButton.getAttribute("data-user-id");
        if (commentUserId != userLoggedId) {
            editButton.classList.add("disabled");
            editButton.setAttribute("onclick", "return false;");
        } else {
            editButton.classList.remove("disabled");
        }
    });

    deleteButtons.forEach(deleteButton => {
        const commentUserId = deleteButton.getAttribute("data-user-id");
        if (commentUserId != userLoggedId && rol == 1) {
            deleteButton.classList.add("disabled");
            deleteButton.setAttribute("onclick", "return false;");
        } else {
            deleteButton.classList.remove("disabled");
        }
    });
}
document.addEventListener('DOMContentLoaded', commentOptions);


//Función para visualización de formulario para editar comentario
function editComment(event) {
    const a = event.target;
    const commentId = a.getAttribute("data-comment-id");

    const allForms = document.querySelectorAll('.show');
    allForms.forEach(form => {
        form.classList.remove('show');
        form.classList.add('form-edit');
    });

    const formEdit = document.getElementById(`div-form-edit-${commentId}`);
    if (formEdit) {
        formEdit.classList.remove('form-edit');
        formEdit.classList.add('show');
    }

}

//Validación de datos ingresados
const allEditForms = document.querySelectorAll('.form-edit-comment');

allEditForms.forEach(form => {
    form.addEventListener('submit', (event) => {
        const content = form.elements['content'].value.trim();
        if (content === "") {
            event.preventDefault();
            const div = document.getElementById("header-comment");
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
});

//Al seleccionar el input se borra el span de campos obligatorios
const contentEditInput = document.getElementById("content-comment");
const contentAddInput = document.getElementById("content");
const inputs = [contentEditInput, contentAddInput];
inputs.forEach( input => {
   if(input){
       input.addEventListener("focus", () => {
    const errorMessage = document.getElementById("error-message");
    if (errorMessage) {
        errorMessage.remove();
    }
}); 
   }
});

//Función para cancelar y cerrar formulario de editar comentario
function cancelEdit(event) {
    const btnCancelar = event.target;

    const commentId = btnCancelar.getAttribute("data-comment-id");
    const form = document.getElementById(`div-form-edit-${commentId}`);
    const inputContent = form.querySelector('input[name="content"]');

    if (form) {
        form.classList.remove('show');
        form.classList.add('form-edit');
        inputContent.value = inputContent.defaultValue;
    }
}
