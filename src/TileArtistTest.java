

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.TreeSet;
import java.util.Set;

/**
 * This tests TileArtist.
 *
 * @author  Josh Gillham
 * @version 11-8-12
 */
public class TileArtistTest {
    /**
     * Provides a way to snoop on the activity of paintComponent.
     */
    class MockDebugGraphics extends javax.swing.DebugGraphics {
        /** Holds a count of the number of times drawLine() has been called. */
        public int totalLines = 0;
        /** Pretends to draw a line, but, really counts calls. */
        public void drawLine( int x1, int y1, int x2, int y2 ){
            ++totalLines;
        }
    };
    
    /**
     * Provides a way to force Logic to behave in ways 
     *  that help the tests.
     */
    class InstrumentLogic extends Logic {
        /** Provide empty constructor. */
        public InstrumentLogic() {
            super( chr, maze );
        }
    }
    /** Holds the dead end. */
    Set< Direction > deadEnd = new TreeSet< Direction >();
    /** Holds the hall way. */
    Set< Direction > hall = new TreeSet< Direction >();
    /** Holds the corner. */
    Set< Direction > corner = new TreeSet< Direction >();
    /** Holds the side. */
    Set< Direction > side = new TreeSet< Direction >();
    /** Holds the test labyrinth. */
    Wall[][] lab = {
      { new Wall( deadEnd ), new Wall( corner ), new Wall( hall ) },
      { new Wall( deadEnd ), new Wall( side ), new Wall( hall ) },
      { new Wall( deadEnd ), new Wall( corner ), new Wall( hall ) },
    };
    /** Holds the starting coordinate. */
    Coordinate start = new Coordinate( 1, 1 );
    /** Holds the test character. */
    Character chr = new Character( start );
    /** Holds the test maze. */
    Maze maze = new Maze( lab );
    
    /**
     * Creates the objects needed to run tests.
     */
    public TileArtistTest() {
        // Compose a dead end with 3 walls.
        deadEnd.add( Direction.North );
        deadEnd.add( Direction.East );
        deadEnd.add( Direction.West );
        
        // Compose a hall way with 2 paralell walls.
        hall.add( Direction.East );
        hall.add( Direction.West );
        
        // Compose a corner with 2 adjacent walls.
        corner.add( Direction.North );
        corner.add( Direction.East );
        
        // Compose a side with only 1 wall.
        side.add( Direction.North );
    }   
    
    /**
     * Proves that the TileArtist can be created for any direction.
     */
    @Test
    public void testConstructor() {
        Logic game = new InstrumentLogic();
        try {
            // For each direction, make sure there are no exceptions.
            for( Direction dir: Direction.values() ) {
                // Create an instance with that direction.
                new TileArtist( game, dir );
            }
        }
        catch( Exception e ) {
            fail( "Should not through an exception." );
        }
    }
    
    
    
    /**
     * Proves that the paintComponent() method will draw lines as expected.
     */
    @Test
    public void testPaintComponent_DrawLines() {
        Logic game = new InstrumentLogic( );
        MockDebugGraphics g;
        // For each direction, make sure paintComponent draws the correct
        //  number of lines.
        for( Direction dir: Direction.values() ) {
            try {
                // Duplicate the player's starting coordinate.
                Coordinate coord = game.getCharacter().getCoordinate().clone();
                // Move the coordinate to the correct position.
                coord.translate( dir );
                // Find the number of walls for that coordinate.
                int size = maze.getWall( coord ).getDirections().size();
                // Call paintComponent.
                new TileArtist( game, Direction.North ).paintComponent( g = new MockDebugGraphics() );
                // paintComponent() should have called drawLine for each wall in coordinate.
                assertEquals( size, g.totalLines );
            }catch( CloneNotSupportedException e ) {
                fail( "Should be cloneable" );
            }   
        }
    }
}
