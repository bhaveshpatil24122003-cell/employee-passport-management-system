const expiringTableBody =
    document.getElementById("expiringTableBody");

const expiringMessage =
    document.getElementById("expiringMessage");


async function loadExpiringPassports() {

    try {

        const response = await fetch("/passports/expiring", {

            method: "GET",

            credentials: "include"

        });


        if (!response.ok) {

            expiringMessage.innerText =
                "Unable to load expiring passports. Status: " +
                response.status;

            expiringMessage.style.color = "red";

            return;
        }


        const passports = await response.json();

        expiringTableBody.innerHTML = "";


        if (passports.length === 0) {

            expiringMessage.innerText =
                "No passports are expiring within the next 30 days.";

            expiringMessage.style.color = "green";

            return;
        }


        passports.forEach(passport => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${passport.passportId}</td>
                <td>${passport.passportNo}</td>
                <td>${passport.issueDate}</td>
                <td>${passport.expiryDate}</td>
                <td>${passport.nationality}</td>
                <td>${passport.employeeId}</td>
            `;

            expiringTableBody.appendChild(row);

        });


        expiringMessage.innerText =
            passports.length +
            " passport(s) expiring within the next 30 days.";

        expiringMessage.style.color = "green";


    } catch (error) {

        expiringMessage.innerText =
            "Server connection failed";

        expiringMessage.style.color = "red";

        console.error(error);

    }

}


loadExpiringPassports();