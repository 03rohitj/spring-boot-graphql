package com.udacity.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.graphql.exception.BreedNotFoundException;
import com.udacity.graphql.exception.DogNotFoundException;
import com.udacity.graphql.model.Dog;
import com.udacity.graphql.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    DogRepository dogRepository;

    public Mutation(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    //Delete all the dogs of a particular breed
    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> dogs = dogRepository.findAll();
        for(Dog dog : dogs){
            if(dog.getBreed().equals(breed)){
                dogRepository.deleteById(dog.getId());
                deleted = true;
            }
        }
        if(deleted == false)
            throw new BreedNotFoundException("Invalid breed input", breed);

        return deleted;
    }

    //Update dog's name
    public Dog updateDogName(Long id, String name){
        Optional<Dog> dogOptional = dogRepository.findById(id);
        if(dogOptional.isPresent()){
           Dog dog = dogOptional.get();
           //Update the dog with new name
           dog.setName(name);
           //Update the record in database
           dogRepository.save(dog);
           return dog;
        }
        else{
             throw new DogNotFoundException("Dog not found",id);
        }
    }

    //Return all the dogs
    public List<Dog> findAllDogs(){
        Iterable<Dog> dogs = dogRepository.findAll();
        return (List<Dog>) dogs;
    }
}
