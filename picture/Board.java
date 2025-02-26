package picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import static java.util.Collections.sort;

public class Board extends JFrame {
    private final int tableSize = 8;
    private final JPanel[][] squares;
    private final List<Piece> pieces;
    private final Piece[][] piecesArray=new Piece[tableSize][tableSize];
    private boolean clicked;

    public Board(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(tableSize, tableSize));

        squares = new JPanel[tableSize][tableSize];
        pieces = new ArrayList<>();

        initializeBoard();
        initializePieces();

        List<Piece> piecesCopy = new ArrayList<>(pieces);
        randomlyPutPiecesInArray(piecesCopy);

        clicked=false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked=true;
                sortPieces();
                placePiecesByArray();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                placePiecesByArray();
            }
        });

        setVisible(true);
    }
    private void initializeBoard() {
        getContentPane().removeAll();
        for (int row = 0; row < tableSize; row++) {
            for (int col = 0; col < tableSize; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                if(row >= 3) {
                    if(col <= 4) {
                        squares[row][col].setBackground(new Color(59, 66, 71, 255));
                    } else {
                        squares[row][col].setBackground(new Color(16, 79, 209, 255));
                    }
                } else {
                    squares[row][col].setBackground(new Color(135, 206, 235));
                }
                add(squares[row][col]);
            }
        }
    }

    private void sortPieces(){

        for (Piece[] value : piecesArray) {
            Arrays.fill(value, null);
        }
        for(Piece piece : pieces){
            piecesArray[piece.getY()][piece.getX()]=piece;
        }
    }
    private void randomlyPutPiecesInArray(List<Piece> pieces) {
        int i=0;
        Random rand = new Random();
        Piece piece;
        while(!pieces.isEmpty()) {
            int x = i%tableSize;
            int y = i/tableSize;
            i++;
            if (x >= 0 && x < tableSize && y >= 0 && y < tableSize) {
                piece = pieces.remove(rand.nextInt(pieces.size()));
                piecesArray[y][x]=piece;
            }
        }
    }
    private void placePiecesByArray() {
        int cellWidth = getWidth() / tableSize;
        int cellHeight = getHeight() / tableSize;
        for(int y=0; y<tableSize; y++) {
            for(int x=0; x<tableSize; x++) {
                if (x >= 0 && x < tableSize && y >= 0 && y < tableSize) {
                    if(piecesArray[y][x]!=null) {
                        ImageIcon icon = piecesArray[y][x].getImageIcon(cellWidth, cellHeight);
                        if (icon != null) {
                            JLabel pieceLabel = new JLabel(icon);
                            squares[y][x].removeAll();
                            squares[y][x].add(pieceLabel, BorderLayout.CENTER);
                        }
                    }else{
                        squares[y][x].removeAll();
                    }
                }
            }
        }
        revalidate();
        repaint();
    }

    private void initializePieces() {
        String water="water.png";
        String dirt="dirt.png";
        String log="log.png";
        String stone="stone.png";
        String leaf="leaf.png";
        String ladder="ladder.png";
        String grass="grass.png";
        String iron="iron.png";
        String fire="fire.png";
        String sand="sand.png";
        pieces.add(new Piece(dirt,0,3));
        pieces.add(new Piece(dirt,1,3));
        pieces.add(new Piece(dirt,3,3));
        pieces.add(new Piece(dirt,3,4));
        pieces.add(new Piece(dirt,4,5));
        pieces.add(new Piece(dirt,5,5));
        pieces.add(new Piece(dirt,5,6));
        pieces.add(new Piece(log,0,1));
        pieces.add(new Piece(log,0,2));
        pieces.add(new Piece(log,0,5));
        pieces.add(new Piece(log,1,5));
        pieces.add(new Piece(leaf,0,0));
        pieces.add(new Piece(leaf,1,0));
        pieces.add(new Piece(leaf,1,1));
        pieces.add(new Piece(water,5,3));
        pieces.add(new Piece(water,6,3));
        pieces.add(new Piece(water,6,4));
        pieces.add(new Piece(water,7,3));
        pieces.add(new Piece(water,7,4));
        pieces.add(new Piece(water,7,5));
        pieces.add(new Piece(sand,4,3));
        pieces.add(new Piece(sand,4,4));
        pieces.add(new Piece(sand,5,4));
        pieces.add(new Piece(sand,6,5));
        pieces.add(new Piece(sand,6,6));
        pieces.add(new Piece(sand,7,6));
        pieces.add(new Piece(sand,7,7));
        pieces.add(new Piece(stone,0,6));
        pieces.add(new Piece(stone,0,7));
        pieces.add(new Piece(stone,1,6));
        pieces.add(new Piece(stone,1,7));
        pieces.add(new Piece(stone,3,5));
        pieces.add(new Piece(stone,4,6));
        pieces.add(new Piece(stone,5,7));
        pieces.add(new Piece(stone,6,7));
        pieces.add(new Piece(iron,2,7));
        pieces.add(new Piece(iron,3,6));
        pieces.add(new Piece(iron,3,7));
        pieces.add(new Piece(iron,4,7));
        pieces.add(new Piece(fire,0,4));
        pieces.add(new Piece(grass,1,2));
        pieces.add(new Piece(grass,3,2));
        pieces.add(new Piece(ladder,2,3));
        pieces.add(new Piece(ladder,2,4));
        pieces.add(new Piece(ladder,2,5));
        pieces.add(new Piece(ladder,2,6));
    }

    private void placePieces() {
        int cellWidth = getWidth() / tableSize;
        int cellHeight = getHeight() / tableSize;
        for (Piece piece : pieces) {
            int x = piece.getX();
            int y = piece.getY();
            if (x >= 0 && x < tableSize && y >= 0 && y < tableSize) {
                ImageIcon icon = piece.getImageIcon(cellWidth, cellHeight);
                if (icon != null) {
                    JLabel pieceLabel = new JLabel(icon);
                    squares[y][x].removeAll();
                    squares[y][x].add(pieceLabel, BorderLayout.CENTER);
                }
            }
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Board("Game Board", 600, 600));
    }
}

class Piece implements Comparable<Piece> {
    private final String imagePath;
    private int x, y;
    private int n_cols;

    public Piece(String imagePath, int x, int y) {
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.n_cols=8;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int get1DIndex(int n_cols){
        return x+y*n_cols;
    }
    public int get1DIndex(){
        return x+y*n_cols;
    }

    public ImageIcon getImageIcon(int width, int height) {
        java.net.URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        System.err.println("Could not find image: " + imagePath);
        return null;
    }

    @Override
    public String toString() {
        return imagePath + " (X:" + x + ", Y:" + y + ")";
    }

    @Override
    public int compareTo(Piece o) {
        return Integer.compare(this.get1DIndex(), o.get1DIndex());
    }
}

/*

    private void initializeBoard() {
        getContentPane().removeAll();
        for (int row = 0; row < tableSize; row++) {
            for (int col = 0; col < tableSize; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                if(row >= 3) {
                    if(col <= 4) {
                        squares[row][col].setBackground(new Color(59, 66, 71, 255));
                    } else {
                        squares[row][col].setBackground(new Color(16, 79, 209, 255));
                    }
                } else {
                    squares[row][col].setBackground(new Color(135, 206, 235));
                }
                add(squares[row][col]);
            }
        }
    }


    private void initializePieces() {
        String water="water.png";
        String dirt="dirt.png";
        String log="log.png";
        String stone="stone.png";
        String leaf="leaf.png";
        String ladder="ladder.png";
        String grass="grass.png";
        String iron="iron.png";
        String fire="fire.png";
        String sand="sand.png";
        pieces.add(new Piece(dirt,0,3));
        pieces.add(new Piece(dirt,1,3));
        pieces.add(new Piece(dirt,3,3));
        pieces.add(new Piece(dirt,3,4));
        pieces.add(new Piece(dirt,4,5));
        pieces.add(new Piece(dirt,5,5));
        pieces.add(new Piece(dirt,5,6));
        pieces.add(new Piece(log,0,1));
        pieces.add(new Piece(log,0,2));
        pieces.add(new Piece(log,0,5));
        pieces.add(new Piece(log,1,5));
        pieces.add(new Piece(leaf,0,0));
        pieces.add(new Piece(leaf,1,0));
        pieces.add(new Piece(leaf,1,1));
        pieces.add(new Piece(water,5,3));
        pieces.add(new Piece(water,6,3));
        pieces.add(new Piece(water,6,4));
        pieces.add(new Piece(water,7,3));
        pieces.add(new Piece(water,7,4));
        pieces.add(new Piece(water,7,5));
        pieces.add(new Piece(sand,4,3));
        pieces.add(new Piece(sand,4,4));
        pieces.add(new Piece(sand,5,4));
        pieces.add(new Piece(sand,6,5));
        pieces.add(new Piece(sand,6,6));
        pieces.add(new Piece(sand,7,6));
        pieces.add(new Piece(sand,7,7));
        pieces.add(new Piece(stone,0,6));
        pieces.add(new Piece(stone,0,7));
        pieces.add(new Piece(stone,1,6));
        pieces.add(new Piece(stone,1,7));
        pieces.add(new Piece(stone,3,5));
        pieces.add(new Piece(stone,4,6));
        pieces.add(new Piece(stone,5,7));
        pieces.add(new Piece(stone,6,7));
        pieces.add(new Piece(iron,2,7));
        pieces.add(new Piece(iron,3,6));
        pieces.add(new Piece(iron,3,7));
        pieces.add(new Piece(iron,4,7));
        pieces.add(new Piece(fire,0,4));
        pieces.add(new Piece(grass,1,2));
        pieces.add(new Piece(grass,3,2));
        pieces.add(new Piece(ladder,2,3));
        pieces.add(new Piece(ladder,2,4));
        pieces.add(new Piece(ladder,2,5));
        pieces.add(new Piece(ladder,2,6));
    }
 */
