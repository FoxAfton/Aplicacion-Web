const token = localStorage.getItem("authToken");

if (!token) {
    alert("No tienes acceso, inicia sesión primero");
    window.location.href = "Login.html";
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "Login.html";
}

