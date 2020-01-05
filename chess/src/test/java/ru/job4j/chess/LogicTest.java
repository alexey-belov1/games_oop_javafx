package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LogicTest {
    @Test
    public void whenMoveTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C8));
        assertThat(logic.move(Cell.C8, Cell.E6), is(true));
    }

    @Test
    public void whenMoveNotEmpty() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C8));
        logic.add(new PawnBlack(Cell.D7));
        assertThat(logic.move(Cell.C8, Cell.E6), is(false));
    }

}
