package controller;

import domain.GameCommand;
import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.position.Position;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class ChessController {

    public void run() {
        OutputView.printStartMessage();
        GameCommand gameCommand = receiveStartOrEndCommand();
        if (gameCommand == GameCommand.START) {
            startGame();
        }
    }

    private GameCommand receiveStartOrEndCommand() {
        GameCommand gameCommand = receiveGameCommand();

        while (gameCommand == GameCommand.MOVE) {
            OutputView.printNotStartedGameMessage();
            gameCommand = receiveGameCommand();
        }
        return gameCommand;
    }

    private GameCommand receiveGameCommand() {
        List<String> userInput = InputView.readUserInput();
        GameCommand gameCommand = GameCommand.of(userInput.get(0));
        return gameCommand;
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        List<String> userInput = InputView.readUserInput();
        GameCommand gameCommand = GameCommand.of(userInput.get(0));

        while (gameCommand != GameCommand.END) {
            executePlayingCommand(chessBoard, userInput);
            userInput = InputView.readUserInput();
            gameCommand = GameCommand.of(userInput.get(0));
        }
    }

    private void executePlayingCommand(ChessBoard chessBoard, List<String> userInput) {
        GameCommand gameCommand = GameCommand.of(userInput.get(0));

        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 게임 진행 중에는 move와 end 명령어만 입력 가능합니다.");
        }

        if (gameCommand == GameCommand.MOVE) {
            executeMoveCommand(chessBoard, userInput);
        }
    }

    private void executeMoveCommand(ChessBoard chessBoard, List<String> userInput) {
        Position start = Position.of(userInput.get(1));
        Position end = Position.of(userInput.get(2));

        chessBoard.movePiece(start, end);
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return chessBoard;
    }
}
