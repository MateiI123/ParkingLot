package com.proiect.parkinglotproject.ejb;

import com.proiect.parkinglotproject.common.UserDto;
import com.proiect.parkinglotproject.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u", User.class
            );
            List<User> users = query.getResultList();
            return copyUsersToDto(users);

        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> dtoList = new ArrayList<>();

        for (User user : users) {
            dtoList.add(new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            ));
        }

        return dtoList;
    }
}