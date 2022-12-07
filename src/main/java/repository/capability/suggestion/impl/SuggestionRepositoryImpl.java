package repository.capability.suggestion.impl;


import entity.capability.Suggestion;
import repository.BaseRepositoryImpl;
import repository.capability.suggestion.SuggestionRepository;



public class SuggestionRepositoryImpl extends BaseRepositoryImpl<Suggestion,Long> implements SuggestionRepository {

    @Override
    public Class<Suggestion> getEntityClass() {
        return Suggestion.class;
    }
}
