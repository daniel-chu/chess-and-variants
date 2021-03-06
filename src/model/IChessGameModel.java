package model;

/**
 * Created by danielchu on 12/30/16.
 */

import model.pieces.IPiece;
import model.pieces.PieceInfo;
import model.players.Team;

/**
 * Interface for the model for a game of chess.
 */
public interface IChessGameModel {

  /**
   * Makes the piece move from its original location to the target location, if the move is valid.
   * Also returns a piece that was taken as a result of this move. Returns null if none.
   *
   * @param fromCol   piece's original column
   * @param fromRow   piece's original row
   * @param targetCol target column
   * @param targetRow target row
   * @return the piece that was taken as a result of this move - if none were taken, returns null.
   * @throws IllegalArgumentException if this move is not valid
   */
  IPiece movePiece(int fromCol, int fromRow, int targetCol, int targetRow) throws
          IllegalArgumentException;

  /**
   * Checks if this move would be invalid due to a check or other criteria specific to the game.
   *
   * @param fromCol   piece's original column
   * @param fromRow   piece's original row
   * @param targetCol target column
   * @param targetRow target row
   * @return if making that move will cause the game to be in an invalid state
   */
  boolean willCauseInvalidStateFromCheck(int fromCol, int fromRow, int targetCol, int targetRow);

  /**
   * Tells us the status of the game.
   *
   * @return the code representing if the game has been won yet, and if so, by who.
   */
  GameStatusCode getGameStatus();

  /**
   * Gets the player whose turn it currently is.
   *
   * @return the Team of the current player
   */
  Team whosTurn();

  /**
   * Gets a 2d array representing the location of all pieces in this game. The first array is the
   * column, the second row. So [3][5] would be col 3 row 5.
   *
   * @return the 2d array representing this game
   */
  PieceInfo[][] getBoard();

  /**
   * Undos the last move.
   */
  void undoLastMove();

  /**
   * Restarts the game.
   */
  void restartGame();

  /**
   * Checks if there is a piece on the given cell.
   *
   * @param cell the cell to check
   * @return true if there is a piece, false otherwise
   */
  boolean hasPieceOnCell(String cell);

  /**
   * Gets the name of this game mode.
   */
  String getGameModeName();

}
