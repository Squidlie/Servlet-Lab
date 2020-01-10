package com.astontech.servlet;

import com.astontech.bo.Vehicle;
import com.astontech.bo.VehicleMake;
import com.astontech.bo.VehicleModel;
import com.astontech.dao.VehicleDAO;
import com.astontech.dao.VehicleMakeDAO;
import com.astontech.dao.VehicleModelDAO;
import com.astontech.dao.mysql.VehicleDAOImpl;
import com.astontech.dao.mysql.VehicleMakeDAOImpl;
import com.astontech.dao.mysql.VehicleModelDAOImpl;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


public class VehicleServlet extends javax.servlet.http.HttpServlet{
    private static VehicleDAO vehicleDAO = new VehicleDAOImpl();
    private static VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();
    private static VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        switch(request.getParameter("formName")) {
            case "addVehicle":
                selectMakeModel(request);
                addVehicle(request);
                break;
            case "updateVehicle":
                switch(request.getParameter("changeVehicle")) {
                    case "update":
                        updateVehicle(request);
                        break;
                    case "delete":
                        deleteVehicle(request);
                        break;
                }
                break;
            default:
                break;
        }
        request.setAttribute("vehicleList", vehicleDAO.getVehicleList());
        request.setAttribute("makeList", vehicleMakeDAO.getVehicleMakeList());
        request.setAttribute("modelList", vehicleModelDAO.getVehicleModelList());
        request.getRequestDispatcher("./vehicle.jsp").forward(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("vehicleList", vehicleDAO.getVehicleList());
        request.setAttribute("makeList", vehicleMakeDAO.getVehicleMakeList());
        request.setAttribute("modelList", vehicleModelDAO.getVehicleModelList());
        request.getRequestDispatcher("./vehicle.jsp").forward(request, response);
    }

    private static void selectMakeModel(HttpServletRequest request){
        int selectedModelId = Integer.parseInt(request.getParameter("selectModel"));
        VehicleModel selectedVehicleModel = vehicleModelDAO.getVehicleModelById(selectedModelId);
        vehicleModelToRequest(request, selectedVehicleModel);
        int selectedMakeId = Integer.parseInt(request.getParameter("selectMake"));
        VehicleMake selectedVehicleMake = vehicleMakeDAO.getVehicleMakeById(selectedMakeId);
        vehicleMakeToRequest(request, selectedVehicleMake);
        request.setAttribute("modelSelected", selectedVehicleModel.getVehicleModelId());
        request.setAttribute("makeSelected", selectedVehicleMake.getVehicleMakeId());
    }

    private static void addVehicle(HttpServletRequest request){
        Vehicle newVehicle = new Vehicle();
        newVehicle.setPurchaseDate(new Date());
        requestToVehicle(request, newVehicle);
        if (vehicleDAO.insertVehicle(newVehicle) > 0)
            request.setAttribute("insertSuccessful", "Vehicle Insert in database successful" );
        else
            request.setAttribute("insertSuccessful", "Vehicle Insert Failed");
        vehicleToRequest(request, newVehicle);
    }

    private static void updateVehicle(HttpServletRequest request){
        Vehicle updateVehicle = new Vehicle();
        updateVehicle.setPurchaseDate(new Date());
        updateVehicle.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
        requestToVehicle(request, updateVehicle);
        if (vehicleDAO.updateVehicle(updateVehicle))
            request.setAttribute("updateSuccessful", "Vehicle Update in Database Successful!");
        else
            request.setAttribute("updateSuccessful", "Vehicle Update Failed!");
    }

    private static void deleteVehicle(HttpServletRequest request){
        Vehicle deleteVehicle = new Vehicle();
        deleteVehicle.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
        if (vehicleDAO.deleteVehicle(deleteVehicle.getVehicleId()))
            request.setAttribute("deleteSuccessful", "Vehicle Deleted from Database!");
        else
            request.setAttribute("deleteSuccessful", "Vehicle Delete Failed!");
    }

    private static void requestToVehicle(HttpServletRequest request, Vehicle vehicle) {
        vehicle.setYear(Integer.parseInt(request.getParameter("year")));
        vehicle.setLicensePlate(request.getParameter("licensePlate"));
        vehicle.setVIN(request.getParameter("vin"));
        vehicle.setColor(request.getParameter("color"));
        VehicleModelDAO vehicleModel = new VehicleModelDAOImpl();
        vehicle.setVehicleModel(vehicleModel.getVehicleModelById(Integer.parseInt(request.getParameter("selectModel"))));
    }

    private static void vehicleToRequest(HttpServletRequest request, Vehicle vehicle){
        request.setAttribute("vehicleId", vehicle.getVehicleId());
        request.setAttribute("year", vehicle.getYear());
        request.setAttribute("licensePlate", vehicle.getLicensePlate());
        request.setAttribute("vin", vehicle.getVIN());
        request.setAttribute("color", vehicle.getColor());
        request.setAttribute("vehicleModelId", vehicle.getVehicleModel().getVehicleModelId());
    }

    private static void vehicleMakeToRequest(HttpServletRequest request, VehicleMake vehicleMake){
        request.setAttribute("vehicleMakeId", vehicleMake.getVehicleMakeId());
        request.setAttribute("vehicleMakeName", vehicleMake.getVehicleMakeName());
    }
    private static void vehicleModelToRequest(HttpServletRequest request, VehicleModel vehicleModel){
        request.setAttribute("vehicleModelId", vehicleModel.getVehicleModelId());
        request.setAttribute("vehicleModelName", vehicleModel.getVehicleModelName());
    }
}
