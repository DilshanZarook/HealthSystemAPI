# HealthSystemAPI

![HealthSystemAPI Diagram](/images/api_diagram.png)

# Introduction
The HealthSystemAPI provides a RESTful web service for managing healthcare-related data. Built using JAX-RS, this API handles HTTP requests and supports CRUD operations on entities like patients, doctors, medical records, appointments, billings, and prescriptions.

# Features
Manage patient information, including medical history and current health status.
Handle doctor data, including specializations.
Track appointments between patients and doctors.
Process billing transactions.
Record medical prescriptions.
Store and update medical records.

# HTTP Methods
GET: Retrieve information about patients, doctors, appointments, billings, prescriptions, or medical records.
POST: Create new entries for any of the available entities.
PUT: Update existing data based on provided IDs.
DELETE: Remove existing entries from the system.
