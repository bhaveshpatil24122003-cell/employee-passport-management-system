const passportTableBody = document.getElementById("passportTableBody");

const passportMessage = document.getElementById("passportMessage");

const addPassportBtn = document.getElementById("addPassportBtn");

const passportForm = document.getElementById("passportForm");

const savePassportBtn = document.getElementById("savePassportBtn");

const cancelPassportBtn = document.getElementById("cancelPassportBtn");

let editingPassportId = null;


passportForm.style.display = "none";


async function loadPassports() {

    try {

        const response = await fetch("/passports", {

            method: "GET",

            credentials: "include"

        });

        if (!response.ok) {

            passportMessage.innerText =
                "Unable to load passports. Status: " + response.status;

            passportMessage.style.color = "red";

            return;
        }

        const passports = await response.json();

        passportTableBody.innerHTML = "";

        passports.forEach(passport => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${passport.passportId}</td>
                <td>${passport.passportNo}</td>
                <td>${passport.issueDate}</td>
                <td>${passport.expiryDate}</td>
                <td>${passport.nationality}</td>
                <td>${passport.employeeId}</td>

                <td>
                    <button onclick="editPassport(${passport.passportId})">
                        Edit
                    </button>

                    <button onclick="deletePassport(${passport.passportId})">
                        Delete
                    </button>
                </td>
            `;

            passportTableBody.appendChild(row);
        });

    } catch (error) {

        passportMessage.innerText =
            "Server connection failed";

        passportMessage.style.color = "red";

        console.error(error);
    }
}


addPassportBtn.addEventListener("click", function () {

    editingPassportId = null;

    clearPassportForm();

    passportForm.style.display = "block";

});



cancelPassportBtn.addEventListener("click", function () {

    editingPassportId = null;

    passportForm.style.display = "none";

    clearPassportForm();

});



savePassportBtn.addEventListener("click", async function () {

    const passport = {

        passportNo: document.getElementById("passportNo").value,

        issueDate: document.getElementById("issueDate").value,

        expiryDate: document.getElementById("expiryDate").value,

        nationality: document.getElementById("nationality").value,

        employeeId: Number(
            document.getElementById("passportEmployeeId").value
        )
    };


    let url = "/passports";
    let method = "POST";


    if (editingPassportId !== null) {

        url = "/passports/" + editingPassportId;

        method = "PUT";
    }


    try {

        const response = await fetch(url, {

            method: method,

            headers: {
                "Content-Type": "application/json"
            },

            credentials: "include",

            body: JSON.stringify(passport)
        });


        if (response.ok) {

            if (editingPassportId === null) {

                passportMessage.innerText =
                    "Passport added successfully";

            } else {

                passportMessage.innerText =
                    "Passport updated successfully";
            }

            passportMessage.style.color = "green";

            editingPassportId = null;

            passportForm.style.display = "none";

            clearPassportForm();

            loadPassports();

        } else {

            const error = await response.text();

            passportMessage.innerText =
                "Request failed. Status: " +
                response.status +
                " " +
                error;

            passportMessage.style.color = "red";
        }

    } catch (error) {

        passportMessage.innerText =
            "Server connection failed";

        passportMessage.style.color = "red";

        console.error(error);
    }

});


function clearPassportForm() {

    document.getElementById("passportNo").value = "";

    document.getElementById("issueDate").value = "";

    document.getElementById("expiryDate").value = "";

    document.getElementById("nationality").value = "";

    document.getElementById("passportEmployeeId").value = "";

}


async function editPassport(passportId) {

    editingPassportId = passportId;

    try {

        const response = await fetch(
            "/passports/" + passportId,
            {
                method: "GET",
                credentials: "include"
            }
        );

        if (!response.ok) {

            passportMessage.innerText =
                "Unable to load passport. Status: " +
                response.status;

            passportMessage.style.color = "red";

            return;
        }

        const passport = await response.json();


        document.getElementById("passportNo").value =
            passport.passportNo;

        document.getElementById("issueDate").value =
            passport.issueDate;

        document.getElementById("expiryDate").value =
            passport.expiryDate;

        document.getElementById("nationality").value =
            passport.nationality;

        document.getElementById("passportEmployeeId").value =
            passport.employeeId;


        passportForm.style.display = "block";


    } catch (error) {

        passportMessage.innerText =
            "Server connection failed";

        passportMessage.style.color = "red";

        console.error(error);
    }
}


async function deletePassport(passportId) {

    const confirmDelete = confirm(
        "Are you sure you want to delete this passport?"
    );

    if (!confirmDelete) {
        return;
    }

    try {

        const response = await fetch(
            "/passports/" + passportId,
            {
                method: "DELETE",
                credentials: "include"
            }
        );

        if (response.ok) {

            passportMessage.innerText =
                "Passport deleted successfully";

            passportMessage.style.color = "green";

            loadPassports();

        } else {

            const error = await response.text();

            passportMessage.innerText =
                "Delete failed. Status: " +
                response.status +
                " " +
                error;

            passportMessage.style.color = "red";
        }

    } catch (error) {

        passportMessage.innerText =
            "Server connection failed";

        passportMessage.style.color = "red";

        console.error(error);
    }
}


loadPassports();