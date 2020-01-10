<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./static/CSS/site.css"/>
    <title>Vehicle</title>
</head>
<body>
<div class="borderOne">
    <form name="addVehicle" action="./vehicle" method="post">
        <input type="hidden" name="formName" value="addVehicle" />
        <input type="hidden" name="makeSelected" value="${vehicleMakeId}" />
        <input type="hidden" name="modelSelected" value="${vehicleModelId}" />
            <select name="selectMake">
                <option value="0">(Select Make)</option>
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
            <br>
            <br>
            <select name="selectModel">
                <option value="0">(Select Model)</option>
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
        <br>
        <br>
        <div>
            Plate:
            <input type="text" name="licensePlate" value="${licensePlate}" placeholder="License Plate" />
        </div>
        <div>
            VIN:
            <input type="text" name="vin" value="${vin}" placeholder="VIN" />
        </div>
        <div>
            Year:
            <input type="text" name="year" value="${year}" placeholder="Year" />
        </div>
        <div>
            Color:
            <input type="text" name="color" value="${color}" placeholder="Color" />
        </div>
        <br>
        <button type="submit">Add Vehicle</button>
        <br>
        <br>
        ${insertSuccessful}
    </form>
</div>
<br>
<div class="borderTwo">
    <div class="formHeader"> <div class="textInput">Plate:</div> <div class="textInput">VIN:</div> <div class="textInput">Year:</div> <div class="textInput">Color:</div> <div class="selectInput">Make:</div> <div class="selectInput">Model:</div></div>
    <hr>
    <c:forEach var="vehicle" items="${vehicleList}">
        <form name="updateVehicle" action="./vehicle" method="post">
            <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}">
            <input type="hidden" name="formName" value="updateVehicle" />
            <input type="text" name="licensePlate" placeholder="${vehicle.licensePlate}">
            <input type="text" name="vin" placeholder="${vehicle.VIN}">
            <input type="text" name="year" placeholder="${vehicle.year}">
            <input type="text" name="color" placeholder="${vehicle.color}">
            <select name="selectMake">
                <c:forEach var="vehicleMake" items="${makeList}">
                    <c:choose>
                        <c:when test="${vehicle.vehicleModel.vehicleMakeId.vehicleMakeId == vehicleMake.vehicleMakeId}">
                            <option selected value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <select name="selectModel">
                <c:forEach var="vehicleModel" items="${modelList}">
                    <c:choose>
                        <c:when test="${vehicle.vehicleModel.vehicleModelId == vehicleModel.vehicleModelId}">
                            <option selected value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <button type="submit" name="changeVehicle" value="update">Update</button>
            <button type="submit" name="changeVehicle" value="delete">Delete</button>
        </form>
    </c:forEach>
    ${updateSuccessful}
    ${deleteSuccessful}
</div>

</div>
</body>
</html>
