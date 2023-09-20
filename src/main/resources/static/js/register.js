function handleRegistration(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const user = {
        username: username,
        email: email,
        password: password,
    };
    fetch('http://localhost:8080/api/v1/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
        if (!response.ok) {
            if (response.status === 409) {
                return response.json();
            } else {
                return response.json();
            }
        }
        localStorage.setItem("connectedUser", JSON.stringify(user));
        window.location.href = "user.html";
    }).then(data => {
        if (data) {
            if (data.status === 409) {
                alert(data.message);
            } else {
                if (data.password) {
                    alert(data.password);
                }
                if (data.username) {
                    alert(data.username);
                }
            }
        }
    }).catch(error => {
        console.error('POST request error:', error);
    });
}

const registrationForm = document.getElementById("registrationForm");
registrationForm.addEventListener("submit", handleRegistration);