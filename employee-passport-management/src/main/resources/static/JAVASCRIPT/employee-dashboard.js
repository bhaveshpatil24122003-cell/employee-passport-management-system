const welcomeMessage =
    document.getElementById("welcomeMessage");

const employeeDepartment =
    document.getElementById("employeeDepartment");

const dashboardMessage =
    document.getElementById("dashboardMessage");

const logoutBtn =
    document.getElementById("logoutBtn");


async function loadEmployeeDashboard() {

    try {

        const response = await fetch("/employees/me", {

            method: "GET",

            credentials: "include"

        });


        if (!response.ok) {

            dashboardMessage.innerText =
                "Unable to load employee details. Status: " +
                response.status;

            dashboardMessage.style.color = "red";

            return;
        }


        const employee = await response.json();


        welcomeMessage.innerText =
            "Welcome, " + employee.name;


        employeeDepartment.innerText =
            "Department: " + employee.department;


    } catch (error) {

        dashboardMessage.innerText =
            "Server connection failed";

        dashboardMessage.style.color = "red";

        console.error(error);

    }

}



logoutBtn.addEventListener("click", async function () {

    try {

        const response = await fetch("/logout", {

            method: "POST",

            credentials: "include"

        });


        if (response.ok) {

            window.location.href = "login.html";

        } else {

            dashboardMessage.innerText =
                "Logout failed. Status: " + response.status;

            dashboardMessage.style.color = "red";

        }

    } catch (error) {

        dashboardMessage.innerText =
            "Server connection failed";

        dashboardMessage.style.color = "red";

        console.error(error);

    }

});


loadEmployeeDashboard();