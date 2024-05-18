# Game Of Life

## Table of Contents
1. [Overview](#Overview)
2. [Main Features](#Main-Features)
3. [Installation](#Installation)
4. [Usage](#Usage)
5. [Class Descriptions](#Class-Descriptions)
    - GameOfLife Class
    - Main class
6. [Example Output](#Example-Output)

## Overview
The Game of Life is a cellular automaton devised by mathematician John Conway. It is played on a rectangular grid of squares where each square, except for side and corner squares, has eight neighbors. Squares in the corners have three neighbors, and squares along the sides have five neighbors. Each square in the grid can either contain a living cell or be empty. The arrangement of living cells on the grid at any point is called a generation. The next generation is computed by examining each square in the current generation and applying a set of rules.

## Main Features
Generation Creation
- Compute the next generation of cells based on the current generation.
- Apply rules to determine whether a cell lives, dies, or is born in the next generation.

Neighbor Counting
- Count the number of living neighbors around a specific cell.
- Handle edge cases for cells located on the borders and corners of the grid.

Living Cell Locations
- Find and return the locations of all living cells in the current generation.
- Represent the positions of living cells as an array of coordinates.

Grid Display
- Print the grid to the console, displaying living cells as 'X' and empty cells as '.'.
- Display the initial generation, next generation, and locations of living cells.

## Installation
To run the Game of Life program, follow these steps:
- Clone the repository or download the source code files.
- Ensure you have a Java development environment set up.
- Compile the Java files using a Java compiler (e.g., javac GameOfLife.java Main.java).
- Run the main class to see the Game of Life in action (e.g., java Main).

## Usage
The main class Main demonstrates the functionality of the Game of Life program:
- Define the initial generation of cells.
- Compute the next generation.
- Find and print the locations of living cells in the next generation.
- Print the grid representations of the initial and next generations.

### Example
```
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
```

## Class Descriptions
The GameOfLife class contains methods for simulating Conway's Game of Life.
```
public static boolean[][] createGeneration(boolean[][] currentGeneration) {
    // Check if the input array is valid
    if (currentGeneration == null || currentGeneration.length == 0 || currentGeneration[0].length == 0) {
        throw new IllegalArgumentException("Invalid input array");
    }
    int rows = currentGeneration.length;
    int cols = currentGeneration[0].length;
    boolean[][] nextGeneration = new boolean[rows][cols];

    // Iterate through each cell in the current generation
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int livingNeighbors = countLivingNeighbors(currentGeneration, i, j);

            // Apply rules to determine the state of the cell in the next generation
            if (currentGeneration[i][j]) {
                nextGeneration[i][j] = livingNeighbors == 2 || livingNeighbors == 3;
            } else {
                nextGeneration[i][j] = livingNeighbors == 3;
            }
        }
    }
    // Return the generated next generation
    return nextGeneration;
}

```
## Purpose and Functionality
- The GameOfLife class is responsible for simulating the core logic of Conway's Game of Life. It contains methods to compute the next generation of cells, count the living neighbors of a cell, and find the locations of all living cells in the current generation.
- createGeneration: Takes the current generation of cells and returns the next generation by applying the Game of Life rules.

countingLivingNeighbors: 
- Counts the number of living neighbors around a specific cell.
```
private static int countLivingNeighbors(boolean[][] generation, int row, int col) {
    int count = 0;
    // Iterate through neighboring cells
    for (int i = Math.max(0, row - 1); i <= Math.min(generation.length - 1, row + 1); i++) {
        for (int j = Math.max(0, col - 1); j <= Math.min(generation[0].length - 1, col + 1); j++) {
            count += generation[i][j] ? 1 : 0;
        }
    }
    // Adjust count if the center cell itself is alive
    if (generation[row][col]) {
        count--;
    }

    // Return the count of living neighbors
    return count;
}
```

findingLivingCellLocations: 
- Finds and returns the locations of all living cells in the current generation.
```
public static int[][] findLivingCellLocations(boolean[][] generation) {
    int count = 0;
    // Count the number of living cells
    for (boolean[] row : generation) {
        for (boolean cell : row) {
            if (cell) {
                count++;
            }
        }
    }

    // Create an array to store the locations of living cells
    int[][] livingCells = new int[count][2];
    int index = 0;

    // Populate the array with the locations of cells
    for (int i = 0; i < generation.length; i++) {
        for (int j = 0; j < generation[0].length; j++) {
            if (generation[i][j]) {
                livingCells[index][0] = i;
                livingCells[index][1] = j;
                index++;
            }
        }
    }
    // Return the array containing the locations of cells
    return livingCells;
}

```

Main Class
- The Main class serves as the entry point for the Game of Life program. It demonstrates how to use the GameOfLife class to create an initial generation, compute the next generation, and display the results.
- main: The main method initializes the grid, computes the next generation, and prints the results.
```
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

```

## Example Output
```
Initial Generation:
. X . 
. . X 
X X X 

Next Generation:
. . X 
X . X 
. X X 

Living Cell Locations:
(0, 2)
(1, 0)
(1, 2)
(2, 1)
(2, 2)

```
