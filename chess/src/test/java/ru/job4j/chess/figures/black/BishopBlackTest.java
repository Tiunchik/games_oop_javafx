package ru.job4j.chess.figures.black;

import org.junit.After;
import org.junit.Assert;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.black.BishopBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

public class BishopBlackTest {
    /**
     * Переменная записывает в себя ссылку на объект выполняющий функции вывода данных в консоль
     */
    private final PrintStream normalout = System.out;
    /**
     * Переменная записывает в себя ссылку на буфер, где может храниться выводимая в консоль информация
     */
    private final ByteArrayOutputStream testout = new ByteArrayOutputStream();

    /**
     * В данном методе вывод в консоль заменяется выводом в созданный ранее буфер.
     */
    @Before
    public void readFromBuffer() {
        System.setOut(new PrintStream(testout));
    }

    /**
     * в данном методе возвращаеться вывод в консоль
     */
    @After
    public void readFromConsole() {
        System.setOut(normalout);
    }

    /**
     * Проверка работы метода position
     */
    @Test
    public void testBishopCreation() {
        Figure bishop = new BishopBlack(Cell.A1);
        Assert.assertEquals(bishop.position(),Cell.A1);
    }
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
}
