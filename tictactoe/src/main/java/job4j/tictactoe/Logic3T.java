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
        var result = false;
        int count = 0;
        if (deltaX == 0 || deltaY == 0) {
            for (var index = 0; index < table.length; index++) {
                count = 0;
                var currentX = startX + deltaX * index;
                var currentY = startY + deltaY * index;
                var cell = table[currentX][currentY];
                if (predicate.test(cell)) {
                    for (int sIndex = 0; sIndex < table.length; sIndex++) {
                        if (predicate.test(table[currentX + deltaY * sIndex][currentY + deltaX * sIndex])) {
                            count++;
                        }
                    }
                }
                if (count == 3) {
                    result = true;
                    break;
                }
            }
        } else {
            for (var index = 0; index < table.length; index++) {
                var currentX = startX + deltaX * index;
                var currentY = startY + deltaY * index;
                var cell = table[currentX][currentY];
                if (predicate.test(cell)) {
                    count++;
                }
            }
            if (count == 3) {
                result = true;
            }
        }
        return result;
    }

    public boolean isWinnerX() {

        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, -1, 1);
    }

    public boolean isWinnerO() {
        return this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkO, 0, 0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    public boolean hasGap() {
        List<Figure3T> temp = Stream.of(table).flatMap(Arrays::stream).collect(Collectors.toList());
        var count = temp.stream().filter(Figure3T::hasMarkO).count() + temp.stream().filter(Figure3T::hasMarkX).count();
        return count != 9;
    }
}
