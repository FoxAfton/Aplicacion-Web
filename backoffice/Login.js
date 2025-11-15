const API_BASE = "https://portfolio-api-three-black.vercel.app/api/v1";

function saveToken(token) {
  localStorage.setItem("authToken", token);
}

async function login({ email, password }) {
  const res = await fetch(`${API_BASE}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }),
  });

  const data = await res.json();

  if (!res.ok) {
    throw new Error(data.message || "Login failed");
  }

  saveToken(data.token);
  return data;
}

document.getElementById("Login").addEventListener("submit", async function (e) {
  e.preventDefault();

  const email = document.getElementById("Correo").value;
  const password = document.getElementById("pass").value;

  try {
    const userData = await login({ email, password });

    alert("Login exitoso");

    window.location.href = "Home.html";

  } catch (err) {
    alert(err.message);
    console.error(err);
  }
});
