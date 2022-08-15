package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GameStoreTest {
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Марио", "Приключения");
    Game game2 = store.publishGame("Принц Персий", "Фэнтези");
    Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game4 = store.publishGame("Чип и Дэйл", "Симулятор");


    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));

    }

    @Test
    public void shouldAddGame4() {

        GameStore store = new GameStore();

        Game game1 = store.publishGame("Марио", "Приключения");
        Game game2 = store.publishGame("Принц Персий", "Фэнтези");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game4 = store.publishGame("Чип и Дэйл", "Симулятор");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
        assertTrue(store.containsGame(game4));

        assertFalse(store.containsGame(null));
    }

    @Test
    public void ShouldGetMostPlayer() {

        GameStore store = new GameStore();


        store.addPlayTime("1", 7);
        store.addPlayTime("2", 4);
        store.addPlayTime("3", 5);

        String expected = "1";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void ShouldGetMostPlayerNull() {


        GameStore store = new GameStore();


        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void shouldGetSumPlayedTime() {

        GameStore store = new GameStore();
        store.addPlayTime("1", 10);
        store.addPlayTime("2", 15);
        store.addPlayTime("3", 20);
        store.addPlayTime("4", 25);
        int expected = 70;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }


    @Test
    public void shouldAddPlayedTimeNegativeException() {

        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Алина", -10);
        });
    }




    @Test
    public void shouldAddPlayedTimeOnePlayer() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Нетология Баттл Онлайн", "Бой");

        store.addPlayTime("1", 15);
        store.addPlayTime("1", 30);
        store.addPlayTime("2", 20);

        String expected = "1";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

}
