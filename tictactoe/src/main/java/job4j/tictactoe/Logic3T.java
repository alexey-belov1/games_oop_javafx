package job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
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

    private boolean horizontalLineWin(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if(this.fillBy(predicate, 0, i, 1, 0)) {
               result = true;
               break;
            }
        }
        return result;
    }

    private boolean verticalLineWin(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            if(this.fillBy(predicate, i, 0, 0, 1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean diagonalLineWin(Predicate<Figure3T> predicate) {
        boolean result = false;
        if(this.fillBy(predicate, 0, 0, 1, 1) ||
                 this.fillBy(predicate, this.table.length - 1, 0, -1, 1)) {
            result = true;
        }
        return result;
    }

    public boolean isWinnerX() {
        return  horizontalLineWin(Figure3T::hasMarkX)  ||
                    verticalLineWin(Figure3T::hasMarkX)  ||
                        diagonalLineWin(Figure3T::hasMarkX);

    }

    public boolean isWinnerO() {
        return  horizontalLineWin(Figure3T::hasMarkO)  ||
                    verticalLineWin(Figure3T::hasMarkO)  ||
                        diagonalLineWin(Figure3T::hasMarkO);

    }

    public boolean hasGap() {
        return Arrays.stream(table)
                .flatMap(Arrays::stream)
                .anyMatch(x -> !x.hasMarkX() && !x.hasMarkO());
    }
}
