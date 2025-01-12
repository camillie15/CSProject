//Validación de datos ingresados
const formComment = document.getElementById("form-add-comment");

formComment.addEventListener("submit", (event) => {
    const content = document.getElementById("content").value.trim();
    if (content === "") {
        event.preventDefault();
        alert("El comentario no debe estar vacio ni contener solo espacios en blanco.");
    }
});

//Función para interaccciones con los botones de editar y eliminar
function commentOptions() {
    event.stopPropagation();

    const editButtons = document.querySelectorAll('.btn-update');
    const deleteButtons = document.querySelectorAll('.btn-delete');
    const userLoggedId = document.getElementById("user-id-logged").getAttribute("data-user-logged-id");

    editButtons.forEach(editButton => {
        const commentUserId = editButton.getAttribute("data-user-id");
        if (commentUserId !== userLoggedId) {
            editButton.classList.add("disabled");
            editButton.setAttribute("onclick", "return false;");
        } else {
            editButton.classList.remove("disabled");
        }
    });

    deleteButtons.forEach(deleteButton => {
        const commentUserId = deleteButton.getAttribute("data-user-id");
        if (commentUserId !== userLoggedId) {
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
            alert("El comentario no debe estar vacío ni contener solo espacios en blanco.");
        }
    });
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
