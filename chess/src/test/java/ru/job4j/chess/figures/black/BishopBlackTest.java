/**
 * Пакет тестов на методы игры chess
 *
 * @author Maksim Tiunchik
 */
package ru.job4j.chess.figures.black;

import org.junit.After;
import org.junit.Assert;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.black.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

/**
 * Класс BishopBlackTest - набор автоматических тестов для фигуры BishopBlack
 *
 * @author Maksim Tiunchik (Senebh@gmail.com)
 * @version 0.1
 */
public class BishopBlackTest {
    /**
     * Проверка работы метода copy
     */
    @Test
    public void testBishopCopy() {
        Figure bishop = new BishopBlack(Cell.C1);
        Cell[] myWay = bishop.way(Cell.C1, Cell.G6);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(myWay, is(expected));
    }

    /**
     * Тест метода way
     */
    @Test
    public void testBishopWay() {
        Figure bishop = new BishopBlack(Cell.A1);
        bishop = bishop.copy(Cell.A6);
        Assert.assertEquals(bishop.position(),Cell.A6);
    }

    /**
     * Тест метода way если указано неверное значение ячейки назначения
     */
    @Test
    public void testBishopWayError() {
        Figure bishop = new BishopBlack(Cell.C1);
        Cell[] myWay = bishop.way(Cell.C1, Cell.G5);
        Assert.assertEquals(bishop.position(),Cell.C1);
    }
    /**
     * Тест метода move если на пути  есть другие фигуры
     */
    @Test
    public void testBishopWayOnHisWay() {
        Logic logic = new Logic();
        Figure bishop = new BishopBlack(Cell.C1);
        Figure rook = new RookBlack(Cell.D2);
        logic.add(bishop);
        logic.add(rook);
        logic.move(Cell.C1,Cell.G5);
        Assert.assertEquals(bishop.position(),Cell.C1);
    }
}

