package com.udacity.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class BreedNotFoundException extends RuntimeException implements GraphQLError {
    //extension is an added information in the stack trace(the the information related  to exception)
    Map<String, Object> extensions;

    public BreedNotFoundException(String message, String invalidBreed){
        super(message);
        extensions.put("Invalid Breed",invalidBreed);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
