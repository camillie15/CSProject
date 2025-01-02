//Método para la interacción de darle "like" de un post
const heartIcons = document.querySelectorAll('.heart-icon');

heartIcons.forEach(icon => {
    icon.addEventListener('click', (event) => {
        const targetIcon = event.target;
        if (targetIcon.classList.contains('bi-heart')) {
            targetIcon.classList.remove('bi-heart');
            targetIcon.classList.add('bi-heart-fill');
        } else {
            targetIcon.classList.remove('bi-heart-fill');
            targetIcon.classList.add('bi-heart');
        }
    });
});