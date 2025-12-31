<!DOCTYPE html>
<html>
<head>
    <title>Teacher Management</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body class="bg-page">
    <div class="container">
        <h1>Teacher Management System</h1>

        <div class="nav">
            <a href="/add">Add Teacher</a>
            <a href="/update">Update Teacher</a>
            <a href="/delete">Delete Teacher</a>
        </div>

        <h2>All Teacher</h2>
        <div id="data">Loading teachers...</div>
    </div>

    <script>
        function loadDepartments() {
            fetch('/departments')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('HTTP error! status: ' + response.status);
                    }
                    return response.json();
                })
                .then(data => {
                    if (!data || data.length === 0) {
                        document.getElementById('data').innerHTML =
                            '<p style="color: #64748b; padding: 20px;">No departments found. Add some departments to get started!</p>';
                        return;
                    }

                    let html = '<table><tr><th>ID</th><th>Name</th><th>Subject</th><th>Experience</th></tr>';
                    data.forEach(d => {
                        html += '<tr>';
                        html += '<td>' + d.id + '</td>';
                        html += '<td>' + d.name + '</td>';
                        html += '<td>' + d.location + '</td>';
                        html += '<td>' + d.budget + '</td>';
                        html += '</tr>';
                    });
                    html += '</table>';
                    document.getElementById('data').innerHTML = html;
                })
                .catch(error => {
                    console.error('Error loading departments:', error);
                    document.getElementById('data').innerHTML =
                        '<p style="color: #ef4444; padding: 20px;">Error loading departments. Please try again.</p>';
                });
        }

        // Load departments when page loads
        loadDepartments();
    </script>
</body>
</html>