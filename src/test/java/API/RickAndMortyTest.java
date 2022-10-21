package API;

import API.hooks.ApiHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static API.steps.RickAndMortySteps.*;

public class RickAndMortyTest extends ApiHooks {
    @Test
    @DisplayName("Задание 1. Работа с API по Rick and Morty.")
    public void Test_RickAndMorty() {
        getCharacterData();
        getLastEpisode();
        getLastCharacterInEpisode();
        getLastCharacterData();

        checkBothSpeciesAreEqual();
        checkBothOnSameLocation();
    }
}
