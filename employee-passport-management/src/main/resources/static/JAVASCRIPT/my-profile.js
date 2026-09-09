const profileName = document.getElementById("profileName");
const profileEmail = document.getElementById("profileEmail");
const profileContact = document.getElementById("profileContact");
const profileDepartment = document.getElementById("profileDepartment");
const profileRole = document.getElementById("profileRole");
const profileMessage = document.getElementById("profileMessage");
const updateProfileBtn = document.getElementById("updateProfileBtn");


async function loadProfile() {

    try {

        const response = await fetch("/employees/me", {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {

            profileMessage.innerText =
                "Unable to load profile. Status: " + response.status;

            profileMessage.style.color = "red";

            return;
        }

        const employee = await response.json();

        profileName.value = employee.name;
        profileEmail.value = employee.email;
        profileContact.value = employee.contactNo;
        profileDepartment.value = employee.department;
        profileRole.value = employee.role;

    } catch (error) {

        profileMessage.innerText = "Server connection failed";
        profileMessage.style.color = "red";

        console.error(error);
    }
}


updateProfileBtn.addEventListener("click", async function () {

    const employee = {
        name: profileName.value,
        contactNo: profileContact.value,
        department: profileDepartment.value
    };

    try {

        const response = await fetch("/employees/me", {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            credentials: "include",

            body: JSON.stringify(employee)
        });

        if (response.ok) {

            profileMessage.innerText =
                "Profile updated successfully";

            profileMessage.style.color = "green";

            loadProfile();

        } else {

            const error = await response.text();

            profileMessage.innerText =
                "Update failed. Status: " +
                response.status + " " + error;

            profileMessage.style.color = "red";
        }

    } catch (error) {

        profileMessage.innerText = "Server connection failed";
        profileMessage.style.color = "red";

        console.error(error);
    }
});


loadProfile();