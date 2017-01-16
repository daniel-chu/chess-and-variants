package view.board;

import java.awt.*;

import javax.swing.*;

import model.pieces.PieceInfo;
import model.players.Team;
import view.board.pieceimagemaps.IPieceImageMaps;
import view.board.pieceimagemaps.StandardBlackPieces;
import view.board.pieceimagemaps.StandardWhitePieces;

/**
 * Created by danielchu on 1/15/17.
 */

/**
 * Panel containing the rendered board and pieces.
 */
public class GamePanel extends JPanel {

  public static final int CELL_SIZE = 75;

  public static final Color BROWN = new Color(170, 105, 25);

  public static final Color DARK_BROWN = new Color(232, 180, 118);

  private final IPieceImageMaps blackPieceImages = new StandardBlackPieces();

  private final IPieceImageMaps whitePieceImages = new StandardWhitePieces();

  /**
   * The 2d array representing the game board.
   */
  private PieceInfo[][] board;

  public GamePanel() {
    super();
    this.board = new PieceInfo[0][0];
  }

  public void setBoard(PieceInfo[][] board) {
    this.board = board;
    this.setPreferredSize(this.findSizes());
    this.revalidate();
    this.invalidate();
  }

  public Dimension findSizes() {
    int width = 0;
    int height = 0;
    if (this.board.length > 0) {
      width = this.board.length * CELL_SIZE;
      height = this.board[0].length * CELL_SIZE;
    }
    return new Dimension(width, height);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintBoard(g);
    paintPieces(g);
  }

  /**
   * Renders the board.
   */
  private void paintBoard(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(BROWN);
    if (board.length > 0) {
      int maxRow = this.board[0].length - 1;
      for (int curRow = 0; curRow < board[0].length; curRow++) {
        for (int curCol = curRow % 2; curCol < board.length; curCol += 2) {
          g2.fillRect((maxRow - curRow) * CELL_SIZE, curCol * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
      }
      g2.setColor(DARK_BROWN);
      for (int curRow = 0; curRow < board[0].length; curRow++) {
        for (int curCol = (curRow + 1) % 2; curCol < board.length; curCol += 2) {
          g2.fillRect((maxRow - curRow) * CELL_SIZE, curCol * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
      }
    }
  }

  /**
   * Renders all pieces.
   */
  private void paintPieces(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (board.length > 0) {
      int maxRow = this.board[0].length - 1;
      for (int row = 0; row < this.board[0].length; row++) {
        for (int col = 0; col < this.board.length; col++) {
          PieceInfo piece = this.board[col][row];
          if (piece.getTeam() == Team.ONE) {
            ImageIcon pieceImage = this.whitePieceImages.getImage(piece.getType());
            pieceImage.paintIcon(this, g, col * CELL_SIZE, (maxRow - row) * CELL_SIZE);
          } else if (piece.getTeam() == Team.TWO) {
            ImageIcon pieceImage = this.blackPieceImages.getImage(piece.getType());
            pieceImage.paintIcon(this, g, col * CELL_SIZE, (maxRow - row) * CELL_SIZE);
          }
        }
      }
    }
  }

}