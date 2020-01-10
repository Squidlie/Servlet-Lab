package com.astontech.servlet;

import com.astontech.bo.Person;
import com.astontech.dao.PersonDAO;
import com.astontech.dao.mysql.PersonDAOImpl;
import common.helpers.ServletHelper;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class PersonServlet extends javax.servlet.http.HttpServlet {

    final static Logger logger = Logger.getLogger(PersonServlet.class);
    private static PersonDAO personDAO = new PersonDAOImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        switch(request.getParameter("formName")) {
            case "choosePerson":
                choosePerson(request);
                break;
            case "updatePerson":
                updatePerson(request);
                break;
            default:
                break;
        }
        request.setAttribute("personList", personDAO.getPersonList());
        request.getRequestDispatcher("./person.jsp").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("personList", personDAO.getPersonList());
//        request.setAttribute("selectPerson", generateDropdown(0));
        request.getRequestDispatcher("./person.jsp").forward(request, response);
    }

    private static void choosePerson(HttpServletRequest request) {
        logger.info("Form #1 - Form Name= " + request.getParameter("formName"));
        ServletHelper.logRequestParams(request);

        int selectedPersonId = Integer.parseInt(request.getParameter("selectPerson"));

        Person selectedPerson = personDAO.getPersonById(selectedPersonId);
        logger.info("Selected Person Details: " + selectedPerson.ToString());

        personToRequest(request, selectedPerson);
//        request.setAttribute("selectPerson", generateDropdown(selectedPerson.getPersonId()));
    }

    private static void updatePerson(HttpServletRequest request) {
        logger.info("Form #2 - Form Name= " + request.getParameter("formName"));
        ServletHelper.logRequestParams(request);

        Person updatePerson = new Person();
        requestToPerson(request, updatePerson);

        logger.info(updatePerson.ToString());
        if (personDAO.updatePerson(updatePerson))
            request.setAttribute("updateSuccessful", "Person Update in Database Successful!");
        else
            request.setAttribute("updateSuccessful", "Person Update Failed!");

        updatePerson = personDAO.getPersonById(updatePerson.getPersonId());
        personToRequest(request, updatePerson);
//        request.setAttribute("selectPerson", generateDropdown(Integer.parseInt(request.getParameter("personId"))));
    }

//    private static String generateDropdown(int selectedPersonId) {
//        StringBuilder strBld = new StringBuilder();
//        strBld.append("<select name='selectPerson'>");
//        strBld.append("<option value='0'>(Select Person)</option>");
//        for(Person person : personDAO.getPersonList()){
//            if (person.getPersonId() == selectedPersonId)
//                strBld.append("<option selected value='").append(person.getPersonId()).append("'>").append(person.getFullName()).append("</option>");
//            else
//                strBld.append("<option value='").append(person.getPersonId()).append("'>").append(person.getFullName()).append("</option>");
//        }
//
//        strBld.append("</select>");
//        return strBld.toString();
//    }

    private static void requestToPerson(HttpServletRequest request, Person person) {
        person.setPersonId(Integer.parseInt(request.getParameter("personId")));
        person.setFirstName(request.getParameter("firstName"));
        person.setLastName(request.getParameter("lastName"));
        person.setDisplayFirstName(request.getParameter("displayFirstName"));
        person.setGender(request.getParameter("gender"));
        person.setTitle(request.getParameter("title"));
    }

    private static void personToRequest(HttpServletRequest request, Person person) {
        request.setAttribute("personId", person.getPersonId());
        request.setAttribute("firstName", person.getFirstName());
        request.setAttribute("lastName", person.getLastName());
        request.setAttribute("displayFirstName", person.getDisplayFirstName());
        request.setAttribute("gender", person.getGender());
        request.setAttribute("title", person.getTitle());
    }
}
