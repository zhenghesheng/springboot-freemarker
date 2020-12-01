<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>${name}</h1>
<#list  userlist  as  user>
    ${user!}<br/>
</#list>
</body>
</html>