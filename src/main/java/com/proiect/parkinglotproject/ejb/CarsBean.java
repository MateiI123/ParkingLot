package com.proiect.parkinglotproject.ejb;

import com.proiect.parkinglotproject.common.CarDto;
import com.proiect.parkinglotproject.entities.Cars;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public static List<CarDto> copyCarsToDto(List<Cars> cars){
        List<CarDto> carsDtoList = new ArrayList<>();
        for(Cars car : cars){

            CarDto carDto = new CarDto(
                    car.getId(),
                    car.getOwner().getUsername(),
                    car.getParkingSpot(),
                    car.getLicensePlate()

            );
            carsDtoList.add(carDto);
        }


        return carsDtoList;
    }
    public List<CarDto> findAllCars(){

        LOG.info("findAllCars");
        try{

            TypedQuery<Cars> typedQuery = entityManager.createQuery("SELECT c FROM Cars c", Cars.class);
            List<Cars> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);

        } catch (Exception ex){
            throw new EJBException(ex);
        }

    }


}
