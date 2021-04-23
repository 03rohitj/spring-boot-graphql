package com.udacity.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.graphql.exception.DogNotFoundException;
import com.udacity.graphql.model.Dog;
import com.udacity.graphql.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository){
        this.dogRepository=dogRepository;
    }

    public Dog findDogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            return optionalDog.get();
        }
        else{
            throw new DogNotFoundException("Dog not found", id);
        }
    }

    public List<Dog> findAllDogs(){
        return (List<Dog>) dogRepository.findAll();
    }
}
