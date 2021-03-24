package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public final class GameSet extends GameState {

    public GameSet(Board board, Side side) {
        super(board, side);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
    }

    @Override
    public State move(Position from, Position to) {
        throw new InvalidCommandException();
    }

    @Override
    public State finished() {
        return new Finished(board(), currentTurn());
    }

    @Override
    public Score score() {
        return Score.from(board());
    }

    @Override
    public Side winner() {
        return board().winner();
    }

    @Override
    public boolean isGameSet() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
