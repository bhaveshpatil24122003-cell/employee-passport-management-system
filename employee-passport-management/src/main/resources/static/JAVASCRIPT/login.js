const loginForm = document.getElementById("loginForm");
const message = document.getElementById("message");

loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },

            credentials: "include",
            body: JSON.stringify({
                email: email,
                password: password
				 })
				});

				if (response.ok) {
				    message.innerText = "Login successful";
				    message.style.color = "green";
				    const profileResponse = await fetch("/employees/me", {
				        method: "GET",
				        credentials: "include"
				    });

				    if (profileResponse.ok) {
				        const employee = await profileResponse.json();

				        if (employee.role === "ADMIN") {
				            window.location.href = "admin-dashboard.html";

				        } else if (employee.role === "EMPLOYEE") {
				            window.location.href = "employee-dashboard.html";

				        }
				    }
				} else {
				    message.innerText = "Invalid email or password";
				    message.style.color = "red";
				}

    } catch (error) {
        message.innerText = "Server connection failed";
        message.style.color = "red";
        console.error(error);
		}
	});