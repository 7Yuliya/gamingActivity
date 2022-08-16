package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {

    //    возвращает суммарное количество часов, проигранное в эту игру
    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    // суммирует время, проигранное во все игры этого жанра этим игроком
    @Test
    public void shouldSumGenreOfSeveralGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("game100", "genre200");
        Game game2 = store.publishGame("game101", "genre200");
        Game game3 = store.publishGame("game102", "genre202");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 3);
        player.play(game3, 3);

//        должна быть сумма по заданному жанру
        int expected = 6;
        int actual = player.sumGenre("genre200");
        assertEquals(expected, actual);
    }


    // суммирует время, проигранное во все игры этого жанра этим игроком,
//    с отрицательным параметром времени
    @Test
    public void shouldSumGenreOfSeveralGamesWithoutANegativeTime() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("game100", "genre200");
        Game game2 = store.publishGame("game101", "genre200");
        Game game3 = store.publishGame("game102", "genre202");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, -1);
        player.play(game2, 3);
        player.play(game3, 3);

//        должна быть сумма по заданному жанру
        int expected = 3;
        int actual = player.sumGenre("genre200");
        assertEquals(expected, actual);
    }


    //    если игра не была установлена, то надо выкидывать RuntimeException
    @Test
    public void shouldGiveAnErrorWhenTheGameIsNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("game100", "genre200");
        Game game1 = store.publishGame("game101", "genre201");

        Player player = new Player("Petya");
        player.installGame(game);
        //game1 не инсталируем

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, 10);
        });
    }


    //      если игра уже была, никаких изменений происходить не должно
    @Test
    public void shouldShowTheTimeWhenYouAddAGameAgain() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("game100", "genre200");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 5);
        player.installGame(game1);

        int expected = 5;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }


    //      возвращает игру этого жанра, в которую играли больше всего
    @Test
    public void shouldShowTheGameWithTheLongesPlayingTimeInOneGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("game100", "genre200");
        Game game2 = store.publishGame("game101", "genre200");
        Game game3 = store.publishGame("game102", "genre202");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 7);
        player.play(game3, 1);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("genre200");
        assertEquals(expected, actual);
    }


    //      Если в игры этого жанра не играли, возвращается null
    @Test
    public void shouldReturnNullIfNotPlayedThisGenreOfGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("game100", "genre200");
        Game game2 = store.publishGame("game101", "genre200");
        Game game3 = store.publishGame("game102", "genre202");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 7);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game3.getGenre());
        assertEquals(expected, actual);
    }
}



