package com.proiect.parkinglotproject.ejb;

import com.proiect.parkinglotproject.common.CarDto;
import com.proiect.parkinglotproject.entities.Cars;
import com.proiect.parkinglotproject.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
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

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Cars car = new Cars();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }

    public CarDto findById(Long carId) {
        LOG.info("findById carId=" + carId);

        try {
            Cars car = entityManager.find(Cars.class, carId);

            if (car == null) {
                return null; // or throw exception if you prefer
            }

            return new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Cars car = entityManager.find(Cars.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser = car.getOwner();
        oldUser.getCars().remove(car);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds carIds");

        for(Long carId : carIds){
            Cars car = entityManager.find(Cars.class, carId);
            entityManager.remove(car);
        }
    }


}
