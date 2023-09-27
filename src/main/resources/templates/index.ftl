<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="/static/style.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="container">
    <div class="margin"></div>
    <div id="content">
        <h1>Please confirm that you're a robot</h1>
        <h2>Please solve the following challenges within <span id = "remaining time"></span> ${counter}</h2>
        <p><code class="codeblock">${chall}</code>
        </p>
        <form action="/" method="post">
            <label for="solution">Solution:</label><br>
            <input type="text" id="solution" name="solution" class="input"><br>
            <input type="submit" value="Submit" class="button">
        </form>
        <code></code>
    </div>
    <div class="margin"></div>
</div>
    

</body>
</html>