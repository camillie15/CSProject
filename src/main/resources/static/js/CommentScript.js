//Validación de datos ingresados
const formComment = document.getElementById("form-add-comment");

formComment.addEventListener("submit", (event) => {
    const content = document.getElementById("content").value.trim();
    if (content === "") {
        event.preventDefault();
        alert("El comentario no debe estar vacio ni contener solo espacios en blanco.");
    }
});

//Función para apertura y cierre de dropdown
function commentOptions(event) {
    event.stopPropagation();
    const button = event.target;
    const commentId = button.getAttribute("data-comment-id");

    const allDropdowns = document.querySelectorAll('.dropdown-content');
    allDropdowns.forEach(dropdown => {
        if (dropdown.id !== `comment-dropdown-${commentId}`) {
            dropdown.classList.remove('show');
        }
    });

    const dropdown = document.getElementById(`comment-dropdown-${commentId}`);

    const commentUserId = button.getAttribute("data-user-id");
    const userLoggedId = document.getElementById("user-id-logged").getAttribute("data-user-logged-id");

    if (commentUserId !== userLoggedId) {
        dropdown.classList.remove("btn-dropdown");
        dropdown.classList.add("disabled");
    } else {
        dropdown.classList.toggle('show');
    }
}

//Cierre de dropdown en un clic en cualquier lugar de la ventana
window.onclick = function (event) {
    if (!event.target.matches('.btn-dropdown') && !event.target.closest('.dropdown')) {
        const dropdowns = document.querySelectorAll('.dropdown-content');
        dropdowns.forEach(dropdown => {
            dropdown.classList.remove('show');
        });
    }
}

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


//Función para cancelar y cerrar formulario de editar comentario
function cancelEdit(event) {
    const btnCancelar = event.target;

    const commentId = btnCancelar.getAttribute("data-comment-id");

    const form = document.getElementById(`div-form-edit-${commentId}`);

    if (form) {
        form.classList.remove('show');
        form.classList.add('form-edit');
    }
}
