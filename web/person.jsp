<%--
  Created by IntelliJ IDEA.
  User: eric
  Date: 1/3/20
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="./static/CSS/site.css"/>
    <title>Person</title>
</head>
<body>
    Form #1:
        choosePerson
    <div class="border">
        <form name="choosePerson" action="./person" method="post">
            <input type="hidden" name="formName" value="choosePerson" />
            <select name="selectPerson">
                <option value="0">(Select Person)</option>
            <c:forEach var="person" items="${personList}">
                <c:choose>
                    <c:when test="${person.personId == personId}">
                        <option selected value="${person.personId}">${person.firstName} ${person.lastName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${person.personId}">${person.firstName} ${person.lastName}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </select>

            ${selectPerson}

            <button type="submit">Select Person</button>
        </form>
    </div>

    Form #2
        updatePerson
    <div class="border">
        <form name="updatePerson" action="./person" method="post">
            <input type="hidden" name="formName" value="updatePerson" />
            <input type="hidden" name="personId" value="${personId}" />
            <div>
                <input type="text" name="firstName" value="${firstName}" placeholder="First Name" />
            </div>
            <div>
                <input type="text" name="lastName" value="${lastName}" placeholder="Last Name" />
            </div>
            <div>
                <input type="text" name="displayFirstName" value="${displayFirstName}" placeholder="Display First Name" />
            </div>
            <div>
                <input type="text" name="gender" value="${gender}" placeholder="Gender" />
            </div>
            <div>
                <input type="text" name="title" value="${title}" placeholder="Title" />
            </div>

            <button type="submit">Update Person</button>
            <br>
            ${updateSuccessful}
        </form>
    </div>
</body>
</html>
