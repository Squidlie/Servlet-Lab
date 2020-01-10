<%--
  Created by IntelliJ IDEA.
  User: eric
  Date: 1/7/20
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="formHeader">
    <div class="formUpdate">Plate
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <input class="inputs" type="text" name="licensePlate" placeholder="${vehicle.licensePlate}" />
        </c:forEach>
    </div>
    <div class="formUpdate">VIN
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <input class="inputs" type="text" name="vin" placeholder="${vehicle.VIN}" />
        </c:forEach>
    </div>
    <div class="formUpdate">Year
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <input class="inputs" type="text" name="year" placeholder="${vehicle.year}" />
        </c:forEach>
    </div>
    <div class="formUpdate">Color
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <input class="inputs" type="text" name="color" placeholder="${vehicle.color}" />
        </c:forEach>
    </div>
    <div class="formUpdate2">Make
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <select class="inputs2" name="selectMake">
                <c:forEach var="vehicleMake" items="${makeList}">
                    <c:choose>
                        <c:when test="${vehicleMake.vehicleMakeId == makeSelected}">
                            <option selected value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </c:forEach>
    </div>
    <div class="formUpdate2">Model
        <br>
        <c:forEach var="vehicle" items="${vehicleList}">
            <select class="inputs2" name="selectModel">
                <c:forEach var="vehicleModel" items="${modelList}">
                    <c:choose>
                        <c:when test="${vehicleModel.vehicleModelId == modelSelected}">
                            <option selected value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </c:forEach>
    </div>
</div>
</body>
</html>
