package model.pieces;

/**
 * Created by danielchu on 12/30/16.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Move;
import model.board.IBoard;
import model.players.Team;

/**
 * Class representing a standard Knight.
 */
public class Knight extends APiece {

  /**
   * Constructor for a Knight piece.
   *
   * @param team the team of this piece
   * @param col  the column this piece will be at
   * @param row  the row this piece will be at
   */
  public Knight(Team team, int col, int row) {
    super(team, col, row, PieceType.KNIGHT);
  }

  @Override
  public boolean validMove(int targetCol, int targetRow, IBoard board) {
    if (!super.validMove(targetCol, targetRow, board)) {
      return false;
    }
    int distCol = Math.abs(this.col - targetCol);
    int distRow = Math.abs(this.row - targetRow);
    if (distCol == 0 || distRow == 0) {
      return false;
    }
    if (distCol + distRow != 3) {
      return false;
    }
    return true;
  }

  @Override
  protected boolean pathFree(int targetCol, int targetRow, IBoard board) {
    // Knights hop over pieces, no need to check path
    return true;
  }

  @Override
  public Set<IPiece> canTakeThese(IBoard board) {
    Set<IPiece> result = new HashSet<IPiece>();
    // up 1 + left 2
    addPieceFromJump(this.col - 1, this.row - 2, result, board);
    // up 1 + right 2
    addPieceFromJump(this.col + 1, this.row - 2, result, board);
    // down 1 + left 2
    addPieceFromJump(this.col - 1, this.row + 2, result, board);
    // down 1 + right 2
    addPieceFromJump(this.col + 1, this.row + 2, result, board);
    // up 1 + left 1
    addPieceFromJump(this.col - 2, this.row - 1, result, board);
    // up 2 + right 1
    addPieceFromJump(this.col + 2, this.row - 1, result, board);
    // down 2 + left 1
    addPieceFromJump(this.col - 2, this.row + 1, result, board);
    // down 2 + right 1
    addPieceFromJump(this.col + 2, this.row + 1, result, board);
    return result;
  }

  @Override
  public List<Move> generateAllPossibleMoves(IBoard board) {
    List<Move> allMoves = new ArrayList<Move>();

    // up 1 + left 2
    addMoveIfValid(this.col - 1, this.row - 2, allMoves, board);
    // up 1 + right 2
    addMoveIfValid(this.col + 1, this.row - 2, allMoves, board);
    // down 1 + left 2
    addMoveIfValid(this.col - 1, this.row + 2, allMoves, board);
    // down 1 + right 2
    addMoveIfValid(this.col + 1, this.row + 2, allMoves, board);
    // up 1 + left 1
    addMoveIfValid(this.col - 2, this.row - 1, allMoves, board);
    // up 2 + right 1
    addMoveIfValid(this.col + 2, this.row - 1, allMoves, board);
    // down 2 + left 1
    addMoveIfValid(this.col - 2, this.row + 1, allMoves, board);
    // down 2 + right 1
    addMoveIfValid(this.col + 2, this.row + 1, allMoves, board);

    return allMoves;
  }

  private void addMoveIfValid(int targetCol, int targetRow, List<Move> allMoves, IBoard board) {
    if (board.validCoordinates(targetCol, targetRow) && this.validMove(targetCol, targetRow, board)) {
      allMoves.add(new Move(team, col, row, targetCol, targetRow));
    }
  }

  /**
   * Adds the piece from the given target to the given list.
   *
   * @param targetCol the target column
   * @param targetRow the target row
   * @param result    the result list
   * @param board     the board we are adding from
   */
  private void addPieceFromJump(int targetCol, int targetRow, Set<IPiece> result, IBoard board) {
    if (board.validCoordinates(targetCol, targetRow) && this.validMove(targetCol, targetRow, board)) {
      IPiece target = board.getPieceAt(targetCol, targetRow);
      if ((target != null) && (target.getTeam() != this.team)) {
        result.add(target);
      }
    }
  }

  @Override
  public IPiece copy() {
    return new Knight(this.getTeam(), this.getCol(), this.getRow());
  }
}
