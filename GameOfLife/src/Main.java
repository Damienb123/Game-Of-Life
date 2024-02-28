
public class Main {

	public static void main(String[] args) {
		// Create an initial generation
        boolean[][] initialGeneration = {
                {false, true, false},
                {false, false, true},
                {true, true, true}
        };

        System.out.println("Initial Generation:");
        printGeneration(initialGeneration);

        // Create and print the next generation
        boolean[][] nextGeneration = GameOfLife.createGeneration(initialGeneration);

        System.out.println("\nNext Generation:");
        printGeneration(nextGeneration);

        // Find and print the locations of living cells
        int[][] livingCellLocations = GameOfLife.findLivingCellLocations(nextGeneration);

        System.out.println("\nLiving Cell Locations:");
        printCellLocations(livingCellLocations);
    }

    // Helper method to print a 2D boolean array
    private static void printGeneration(boolean[][] generation) {
        for (boolean[] row : generation) {
            for (boolean cell : row) {
                System.out.print(cell ? "X " : ". ");
            }
            System.out.println();
        }
    }

    // Helper method to print the locations of living cells
    private static void printCellLocations(int[][] cellLocations) {
        for (int[] location : cellLocations) {
            System.out.println("(" + location[0] + ", " + location[1] + ")");
        }
	}
}
