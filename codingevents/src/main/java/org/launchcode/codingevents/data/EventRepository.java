package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//This is the interface that allows us to react with our MySql database
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
