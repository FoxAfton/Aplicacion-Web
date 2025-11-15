const API_BASE = "https://portfolio-api-three-black.vercel.app/api/v1";

document.getElementById("Registro").addEventListener("submit", async function(e) {
    e.preventDefault();

    const name = document.getElementById("nameR").value;
    const email = document.getElementById("correo").value;
    const password = document.getElementById("passR").value;
    const itsonId = document.getElementById("itsonId").value;

    const user = { name, email, itsonId, password };

    try {
        const res = await fetch(`${API_BASE}/auth/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user)
        });

        const data = await res.json();

        if (!res.ok) {
            alert(data.message || "Error al registrarse");
            return;
        }

        alert("Usuario registrado correctamente");
        window.location.href = "Login.html";

    } catch (err) {
        console.error(err);
        alert("Error de conexión con la API");
    }
});
