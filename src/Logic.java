
/**
 * Acts as the central hub of the game.
 * 
 * @author Josh Gillham
 * @version 10-29-12
 */
public class Logic {
    /** Used to signal an illegal move direction. */
    class BadDirectionException extends Exception { }
    
    /**
     * Initializes the class.
     * 
     * Post Conditions:
     * -character is at the starting position.
     * 
     * @param character is the player.
     * @param maze is the maze.
     * 
     * @throws NullPointerException if either character or maze is null.
     * @throws IllegalArgumentException if the characters position is 
     *  off the map.
     */
    public Logic( Character character, Maze maze ) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Causes the player to move in the direction specified.
     * 
     * @param direction is the direction to move.
     * 
     * @throw BadDirectionException when the direction is not 
     *  allowed by the maze.
     */
    public void makeMove( Direction direction ) throws BadDirectionException {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Gets the character.
     * 
     * @return the character.
     */
    public Character getCharacter() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Accesses the maze.
     * 
     * @return the maze.
     */
    public Maze getMaze() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Sets the GameEvents listener
     * 
     * @param listener the new listener (could be null).
     */
    public void setGameEventsListener( GameEvent listener ) {
        throw new UnsupportedOperationException();
    }
}

