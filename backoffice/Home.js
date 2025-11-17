const API_BASE = "https://portfolio-api-three-black.vercel.app/api/v1";
const token = localStorage.getItem("authToken");

if (!token) {
    alert("No tienes acceso, inicia sesión primero");
    window.location.href = "Login.html";
}

const projectList = document.getElementById("projectList");
const formProject = document.getElementById("formProject");
const formulario = document.getElementById("formulario");
const btnAgregar = document.getElementById("aggProyecto");
const btnCancelar = document.getElementById("cancelForm");

let editingProjectId = null;

async function getProjects() {
    const res = await fetch(`${API_BASE}/projects`, {
        headers: { "auth-token": token }
    });

    if (!res.ok) {
        console.error("Error cargando proyectos");
        return;
    }

    const data = await res.json();
    renderProjects(data);
}

async function getProjectById(id) {
    const res = await fetch(`${API_BASE}/projects/${id}`, {
        headers: { "auth-token": token }
    });

    if (!res.ok) throw new Error("Proyecto no encontrado");

    return res.json();
}

async function createProject(project) {
    const res = await fetch(`${API_BASE}/projects`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "auth-token": token
        },
        body: JSON.stringify(project)
    });

    const data = await res.json().catch(() => null);

    if (!res.ok) {
        console.error("Error del servidor:", data);
        throw new Error(data?.message || "Error al crear proyecto");
    }

    return data;
}

async function updateProject(id, project) {
    const res = await fetch(`${API_BASE}/projects/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "auth-token": token
        },
        body: JSON.stringify(project)
    });

    if (!res.ok) throw new Error("Error al actualizar proyecto");
    return res.json();
}

async function deleteProject(id) {
    if (!confirm("¿Eliminar este proyecto?")) return;

    const res = await fetch(`${API_BASE}/projects/${id}`, {
        method: "DELETE",
        headers: {
            "auth-token": token
        }
    });

    if (!res.ok) {
        alert("Error al eliminar");
        return;
    }

    getProjects();
}

function renderProjects(projects) {
    projectList.innerHTML = "";

    projects.forEach(p => {
        const card = document.createElement("div");
        card.className = "project-card";

        card.innerHTML = `
            <h3>${p.name}</h3>
            <p>${p.description}</p>
            <p><strong>Tecnologías:</strong> ${p.technologies || "N/A"}</p>
            <p><strong>Repo:</strong> ${p.repository || "N/A"}</p>

            <button class="btn-success" onclick="editProject('${p._id}')">Editar</button>
            <button class="btn-danger" onclick="deleteProject('${p._id}')">Eliminar</button>
        `;

        projectList.appendChild(card);
    });
}

btnAgregar.addEventListener("click", () => {
    editingProjectId = null;
    formulario.reset();
    formProject.style.display = "block";
});

btnCancelar.addEventListener("click", () => {
    formProject.style.display = "none";
    formulario.reset();
});

async function editProject(id) {
    const project = await getProjectById(id);

    editingProjectId = id;

    document.getElementById("title").value = project.title;
    document.getElementById("description").value = project.description;
    document.getElementById("tech").value = project.technologies.join(", ");
    document.getElementById("repo").value = project.repository;

    formProject.style.display = "block";
}   

formulario.addEventListener("submit", async (e) => {
    e.preventDefault();

    const project = {
        title: document.getElementById("title").value.trim(),
        description: document.getElementById("description").value.trim(),
        repository: document.getElementById("repo").value.trim(),
        technologies: document.getElementById("tech").value
            .split(",")
            .map(t => t.trim())
            .filter(t => t.length > 0)
    };

    try {
        if (editingProjectId) {
            await updateProject(editingProjectId, project);
        } else {
            await createProject(project);
        }

        formProject.style.display = "none";
        formulario.reset();
        getProjects();

    } catch (err) {
        alert(err.message);
    }
});


document.addEventListener("DOMContentLoaded", getProjects);

document.getElementById("logoutBtn").addEventListener("click", () => {
    if (confirm("¿Seguro que quieres cerrar sesión?")) {
        localStorage.removeItem("authToken");
        window.location.href = "Login.html";
    }
});