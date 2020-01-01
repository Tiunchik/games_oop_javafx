package job4j.tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        var rsl = false;

        for (int index = 0; index < table.length && !rsl; index++) {
            rsl = this.fillBy(Figure3T::hasMarkX, 0, index, 1, 0);
        }

        for (int index = 0; index < table.length && !rsl; index++) {
            rsl = this.fillBy(Figure3T::hasMarkX, index, 0, 0, 1);
        }

        if (!rsl) {
            rsl = this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1);
        }
        if (!rsl) {
            rsl = this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
        }
        return rsl;
    }

    public boolean isWinnerO() {
        var rsl = false;
        for (int index = 0; index < table.length && !rsl; index++) {
            rsl = this.fillBy(Figure3T::hasMarkO, 0, index, 1, 0);
        }

        for (int index = 0; index < table.length && !rsl; index++) {
            rsl =  this.fillBy(Figure3T::hasMarkO, index, 0, 0, 1);
        }

        if (!rsl) {
            rsl = this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1);
        }
        if (!rsl) {
            rsl = this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
        }
        return rsl;
    }

    public boolean hasGap() {
        List<Figure3T> temp = Stream.of(table).flatMap(Arrays::stream).collect(Collectors.toList());
        var count = temp.stream().filter(Figure3T::hasMarkO).count() + temp.stream().filter(Figure3T::hasMarkX).count();
        return count != 9;
    }
}
