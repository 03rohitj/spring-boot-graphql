package com.udacity.graphql.repository;

import com.udacity.graphql.model.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DogRepository extends CrudRepository<Dog,Long> {

  //We don't need any custom query in this case, because we can request required features from GraphQL query. That's the power of GraphQL
}
