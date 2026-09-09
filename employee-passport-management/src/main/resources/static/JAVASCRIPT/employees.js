const employeeTableBody = document.getElementById("employeeTableBody");

const employeeMessage = document.getElementById("employeeMessage");

const addEmployeeBtn = document.getElementById("addEmployeeBtn");

const employeeForm = document.getElementById("employeeForm");

const saveEmployeeBtn = document.getElementById("saveEmployeeBtn");

const cancelEmployeeBtn = document.getElementById("cancelEmployeeBtn");

let editingEmployeeId = null;

employeeForm.style.display = "none";


async function loadEmployees() {

    try {

        const response = await fetch("/employees", {

            method: "GET",

            credentials: "include"

        });

        if (!response.ok) {

            employeeMessage.innerText =
                "Unable to load employees. Status: " + response.status;

            employeeMessage.style.color = "red";

            return;
        }

        const employees = await response.json();

        employeeTableBody.innerHTML = "";

        employees.forEach(employee => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${employee.employeeId}</td>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.contactNo}</td>
                <td>${employee.department}</td>
                <td>${employee.role}</td>

                <td>
                    <button onclick="editEmployee(${employee.employeeId})">
                        Edit
                    </button>

                    <button onclick="deleteEmployee(${employee.employeeId})">
                        Delete
                    </button>
                </td>
            `;

            employeeTableBody.appendChild(row);
        });

    } catch (error) {

        employeeMessage.innerText = "Server connection failed";

        employeeMessage.style.color = "red";

        console.error(error);
    }
}



addEmployeeBtn.addEventListener("click", function () {

    editingEmployeeId = null;

    clearEmployeeForm();

    employeeForm.style.display = "block";
});



cancelEmployeeBtn.addEventListener("click", function () {

    editingEmployeeId = null;

    employeeForm.style.display = "none";

    clearEmployeeForm();
});


saveEmployeeBtn.addEventListener("click", async function () {

    const employee = {

        name: document.getElementById("employeeName").value,

        email: document.getElementById("employeeEmail").value,

        contactNo: document.getElementById("employeeContact").value,

        department: document.getElementById("employeeDepartment").value,

        password: document.getElementById("employeePassword").value,

        role: document.getElementById("employeeRole").value
    };

    let url = "/employees";
    let method = "POST";

    if (editingEmployeeId !== null) {

        url = "/employees/" + editingEmployeeId;

        method = "PUT";
    }

    try {

        const response = await fetch(url, {

            method: method,

            headers: {
                "Content-Type": "application/json"
            },

            credentials: "include",

            body: JSON.stringify(employee)
        });

        if (response.ok) {

            if (editingEmployeeId === null) {

                employeeMessage.innerText =
                    "Employee added successfully";

            } else {

                employeeMessage.innerText =
                    "Employee updated successfully";
            }

            employeeMessage.style.color = "green";

            editingEmployeeId = null;

            employeeForm.style.display = "none";

            clearEmployeeForm();

            loadEmployees();

        } else {

            const error = await response.text();

            employeeMessage.innerText =
                "Request failed. Status: " +
                response.status +
                " " +
                error;

            employeeMessage.style.color = "red";
        }

    } catch (error) {

        employeeMessage.innerText =
            "Server connection failed";

        employeeMessage.style.color = "red";

        console.error(error);
    }
});



function clearEmployeeForm() {

    document.getElementById("employeeName").value = "";

    document.getElementById("employeeEmail").value = "";

    document.getElementById("employeeContact").value = "";

    document.getElementById("employeeDepartment").value = "";

    document.getElementById("employeePassword").value = "";

    document.getElementById("employeeRole").value = "EMPLOYEE";
}


// ==========================
// EDIT EMPLOYEE
// ==========================

async function editEmployee(employeeId) {

    editingEmployeeId = employeeId;

    try {

        const response = await fetch(
            "/employees/" + employeeId,
            {
                method: "GET",
                credentials: "include"
            }
        );

        if (!response.ok) {

            employeeMessage.innerText =
                "Unable to load employee. Status: " +
                response.status;

            employeeMessage.style.color = "red";

            return;
        }

        const employee = await response.json();

        document.getElementById("employeeName").value =
            employee.name;

        document.getElementById("employeeEmail").value =
            employee.email;

        document.getElementById("employeeContact").value =
            employee.contactNo;

        document.getElementById("employeeDepartment").value =
            employee.department;

        document.getElementById("employeeRole").value =
            employee.role;

        document.getElementById("employeePassword").value = "";

        employeeForm.style.display = "block";

    } catch (error) {

        employeeMessage.innerText =
            "Server connection failed";

        employeeMessage.style.color = "red";

        console.error(error);
    }
}



async function deleteEmployee(employeeId) {

    const confirmDelete = confirm(
        "Are you sure you want to delete this employee?"
    );

    if (!confirmDelete) {
        return;
    }

    try {

        const response = await fetch(
            "/employees/" + employeeId,
            {
                method: "DELETE",
                credentials: "include"
            }
        );

        if (response.ok) {

            employeeMessage.innerText =
                "Employee deleted successfully";

            employeeMessage.style.color = "green";

            loadEmployees();

        } else {

            const error = await response.text();

            employeeMessage.innerText =
                "Delete failed. Status: " +
                response.status +
                " " +
                error;

            employeeMessage.style.color = "red";
        }

    } catch (error) {

        employeeMessage.innerText =
            "Server connection failed";

        employeeMessage.style.color = "red";

        console.error(error);
    }
}


loadEmployees();