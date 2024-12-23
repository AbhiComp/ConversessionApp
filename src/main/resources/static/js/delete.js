document.getElementById('userForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    deleteUser(email, password);
});

function deleteUser(email, password) {
    fetch('https://conversessionapp-production.up.railway.app/api/v1/users/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, password: password })
    })
    .then((response) => {
        return response;
    })
    .then((data) => {
        window.location.href = "login.html";
    });
}
