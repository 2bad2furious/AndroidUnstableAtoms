package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameImpl implements Game {

    private GameBoard gameBoard;
    private Player playerOnTurn;

    public static GameImpl createNew(int rowCount, int columnCount) {
        return new GameImpl(rowCount, columnCount);
    }

    private GameImpl(int rowCount, int columnCount) {

        List<List<GameField>> fields = new ArrayList<>();

        // TODO fill up fields with empty GameFields - done

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                fields.get(i).add(j,GameField.createBlank());
            }
        }

        gameBoard = GameBoard.create(fields); // TODO replace with GameBoard.create(fields); - done?
        playerOnTurn = Player.FIRST_PLAYER;
    }


    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y) {
        if (!isMovePossible(x, y)) {
            throw new IllegalStateException("Impossible to make move to position x: " + x + " y: " + y);
        }
        // TODO return new GameBoard after this move
        List<List<GameField>> field = gameBoard.fields();
        GameField gf = field.get(x).get(y);

        field.get(x).add(y, GameField.create(gf.atomCount()+1,playerOnTurn));

        switchPlayerOnTurn();
        return GameBoard.create(field);
        /*return  getSampleBoard(); // TODO replace with new and CORRECT GameBoard - done*/
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        List<List<GameField>> field = gameBoard.fields();
        if(x < 0 || x >= field.size() || y < 0 || y >= field.get(0).size() || (field.get(x).get(y).player() != playerOnTurn && field.get(x).get(y).player() != Player.ANON))
            return false;
        // TODO return if - done
        return true;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }
}
