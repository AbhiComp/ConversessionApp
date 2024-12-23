document.getElementById('updatePasswordForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const oldPassword = document.getElementById('oldPassword').value;
    const newPassword = document.getElementById('newPassword').value;

    updatePassword(email, oldPassword, newPassword);
});

function updatePassword(email, oldPassword, newPassword) {
    fetch('https://conversessionapp-production.up.railway.app/api/v1/users/updatePassword', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, password: oldPassword, newPassword: newPassword })
    })
    .then((response) => {
        if (response.ok) {
            // HTTP status 200
            alert('Password updated successfully');
            window.location.href = "login.html";
        } else {
            // Any other status code
            alert('Failed to update password. Please check your credentials and try again.');
        }
        return response.json(); // Optional: Handle JSON if needed
    })
    .then((data) => {
        console.log('Response data:', data);
    });
}