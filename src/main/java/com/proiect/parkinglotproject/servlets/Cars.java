package com.proiect.parkinglotproject.servlets;

import com.proiect.parkinglotproject.common.CarDto;
import com.proiect.parkinglotproject.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Cars", value = "/Cars")
public class Cars extends HttpServlet {

    @Inject
    CarsBean  carsBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


        List<CarDto> carsList = carsBean.findAllCars();
        request.setAttribute("carsList", carsList);
        request.setAttribute("numberOfFreeParkingSpots", 10);
        request.getRequestDispatcher("/WEB-INF/Pages/cars.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] carIdsAsString = request.getParameterValues("carIds");
        if(carIdsAsString != null){
            List<Long> carIds = new ArrayList<>();
            for(String carIdAsString : carIdsAsString){
                carIds.add(Long.parseLong(carIdAsString));
            }
            carsBean.deleteCarsByIds(carIds);

        }
        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}