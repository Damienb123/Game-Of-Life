/*
 * The Game of Life
 * 
 * is played on a rectangular grid
 * of squares. 
 * Except for side and corner squares, each square has eight 
 * neighbors. Squares in the corners of a grid
 * each have three neighbors, and squares along the 
 * side of a grid each have five neighbors.
 * Each square in a grid can be in one of two states; it either contains a 
 * living cell or does not contain a living cell. The
 * arrangement of living cells on a grid is called a generation. 
 * Given a generation, the next generation is computed by
 * examining each square in the generation and applying the following rules
 */

public class GameOfLife {
public static boolean[][] createGeneration(boolean[][] currentGeneration) {
    	
    	//check if the input array is valid
    	if(currentGeneration == null || currentGeneration.length == 0 || currentGeneration[0].length == 0) {
    		throw new IllegalArgumentException("Invalid input array");
    	}
        int rows = currentGeneration.length;
        int cols = currentGeneration[0].length;
        boolean[][] nextGeneration = new boolean[rows][cols];

        //iterate through each cell in the current generation
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int livingNeighbors = countLivingNeighbors(currentGeneration, i, j);

                /*
                 * apply rules to determine the state of the cell
                 * in the next generation
                 */
                if (currentGeneration[i][j]) {
                    nextGeneration[i][j] = livingNeighbors == 2 || livingNeighbors == 3;
                } else {
                    nextGeneration[i][j] = livingNeighbors == 3;
                }
            }
        }
        //return the generated next generation
        return nextGeneration;
    }

    //counts the number of living neighbors around a specific cell
    private static int countLivingNeighbors(boolean[][] generation, int row, int col) {
        int count = 0;
        //iterate through neighboring cells
        for (int i = Math.max(0, row - 1); i <= Math.min(generation.length - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(generation[0].length - 1, col + 1); j++) {
                count += generation[i][j] ? 1 : 0;
            }
        }
        //adjust count if the center cell itself is alive
        if (generation[row][col]) {
            count--;
        }
        
        //return the count of living neighbors
        return count;
    }

    //find the locations of living cells in the current generation
    public static int[][] findLivingCellLocations(boolean[][] generation) {
        int count = 0;
        //count the number of living cells
        for (boolean[] row : generation) {
            for (boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
        }

        //create an array to store the locations of living cells
        int[][] livingCells = new int[count][2];
        int index = 0;

        //populate the array with the locations of cells
        for (int i = 0; i < generation.length; i++) {
            for (int j = 0; j < generation[0].length; j++) {
                if (generation[i][j]) {
                    livingCells[index][0] = i;
                    livingCells[index][1] = j;
                    index++;
                }
            }
        }
        //return the array containing the locations of cells
        return livingCells;
    }

}
