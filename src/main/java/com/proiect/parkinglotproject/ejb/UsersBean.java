package com.proiect.parkinglotproject.ejb;

import com.proiect.parkinglotproject.common.UserDto;
import com.proiect.parkinglotproject.entities.User;
import com.proiect.parkinglotproject.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @Inject
    PasswordBean passwordBean;

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


    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));

        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");

        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
}