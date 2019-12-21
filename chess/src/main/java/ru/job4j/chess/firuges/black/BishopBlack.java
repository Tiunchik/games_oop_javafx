package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.lang.System.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        try {
            isDiagonal(source, dest);
        } catch (IllegalStateException my) {
        }
        int size = (source.x - dest.x > 0) ? source.x - dest.x : (source.x - dest.x) * -1;
        Cell[] steps = new Cell[size];
        int deltaX = (source.x - dest.x > 0) ? -1 : 1;
        int deltaY = (source.y - dest.y > 0) ? -1 : 1;
        for (int index = 0; index < size; index++) {
            steps[index] = Cell.findBy(source.x + deltaX * (index + 1), source.y + deltaY * (index + 1));
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        boolean answer = false;
        int x = (source.x - dest.x > 0) ? source.x - dest.x : (source.x - dest.x) * -1;
        int y = (source.y - dest.y > 0) ? source.y - dest.y : (source.y - dest.y) * -1;
        if (x == y) {
            answer = true;
        }
        if (!answer) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        return answer;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
