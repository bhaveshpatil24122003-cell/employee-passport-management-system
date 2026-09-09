const employeeCount =
    document.getElementById("employeeCount");

const passportCount =
    document.getElementById("passportCount");

const expiringCount =
    document.getElementById("expiringCount");

const adminMessage =
    document.getElementById("adminMessage");

const logoutBtn =
    document.getElementById("logoutBtn");

async function loadAdminDashboard() {

    try {

        const employeeResponse = await fetch("/employees", {

            method: "GET",

            credentials: "include"

        });

        const passportResponse = await fetch("/passports", {

            method: "GET",

            credentials: "include"

        });


        const expiringResponse = await fetch("/passports/expiring", {

            method: "GET",

            credentials: "include"

        });


        if (!employeeResponse.ok ||
            !passportResponse.ok ||
            !expiringResponse.ok) {

            adminMessage.innerText =
                "Unable to load dashboard data";

            adminMessage.style.color = "red";

            return;
        }


        const employees =
            await employeeResponse.json();

        const passports =
            await passportResponse.json();

        const expiringPassports =
            await expiringResponse.json();

        employeeCount.innerText =
            employees.length;

        passportCount.innerText =
            passports.length;

        expiringCount.innerText =
            expiringPassports.length;


    } catch (error) {

        adminMessage.innerText =
            "Server connection failed";

        adminMessage.style.color = "red";

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

            adminMessage.innerText =
                "Logout failed. Status: " + response.status;

            adminMessage.style.color = "red";

        }

    } catch (error) {

        adminMessage.innerText =
            "Server connection failed";

        adminMessage.style.color = "red";

        console.error(error);

    }

});

loadAdminDashboard();