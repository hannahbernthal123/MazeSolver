// Hannah Bernthal - 2026
/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */

    // This returns the solution by tracing through the parents
    public ArrayList<MazeCell> getSolution() {
        // ArrayList + Stack are utilized to reverse parent order for solution
        ArrayList<MazeCell> path = new ArrayList<MazeCell>();
        MazeCell nextCell = maze.getEndCell();
        Stack<MazeCell> reverse = new Stack<MazeCell>();
        while (nextCell != maze.getStartCell()) {
            reverse.push(nextCell);
            nextCell = nextCell.getParent();
        }
        reverse.push(maze.getStartCell());

        // While there are things in the stack, adds them back to path (in reverse order)
        while (!reverse.empty()) {
            path.add(reverse.pop());
        }
        return path;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Uses a stack to keep track of where it should go next
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        MazeCell currentCell = maze.getStartCell();

        while(currentCell != maze.getEndCell()) {
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // This checks North
            if (maze.isValidCell(row - 1, col)) {
                cellsToVisit.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(currentCell);
                maze.getCell(row - 1, col).setExplored(true);
            }
            // This checks East
            if (maze.isValidCell(row, col + 1)) {
                cellsToVisit.push(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(currentCell);
                maze.getCell(row, col + 1).setExplored(true);
            }
            // This checks South
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.push(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(currentCell);
                maze.getCell(row + 1, col).setExplored(true);
            }
            // This checks West
            if (maze.isValidCell(row, col - 1)) {
                cellsToVisit.push(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(currentCell);
                maze.getCell(row, col - 1).setExplored(true);
            }

            currentCell = cellsToVisit.pop();
        }

        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Uses a queue to keep track of where it should go next
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        MazeCell currentCell = maze.getStartCell();

        // This part is the same as DFS
        while(currentCell != maze.getEndCell()) {
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // This checks North
            if (maze.isValidCell(row - 1, col)) {
                cellsToVisit.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(currentCell);
                maze.getCell(row - 1, col).setExplored(true);
            }
            // This checks East
            if (maze.isValidCell(row, col + 1)) {
                cellsToVisit.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(currentCell);
                maze.getCell(row, col + 1).setExplored(true);
            }
            // This checks South
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.add(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(currentCell);
                maze.getCell(row + 1, col).setExplored(true);
            }
            // This checks West
            if (maze.isValidCell(row, col - 1)) {
                cellsToVisit.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(currentCell);
                maze.getCell(row, col - 1).setExplored(true);
            }
            currentCell = cellsToVisit.remove();
        }

        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze4.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
