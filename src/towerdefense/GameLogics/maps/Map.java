package towerdefense.gamelogics.maps;

import towerdefense.gamelogics.Tile;

/**
 * Enumclass for the different maps that stores some information about them.
 */
public enum Map {
    MAP1("map 1",
            new Checkpoint[]{
            new Checkpoint(10, 1, Direction.LEFT),
            new Checkpoint(8, 1, Direction.DOWN),
            new Checkpoint(8, 4, Direction.LEFT),
            new Checkpoint(5, 4, Direction.UP),
            new Checkpoint(5, 1, Direction.LEFT),
            new Checkpoint(1, 1, Direction.DOWN),
            new Checkpoint(1, 8, Direction.RIGHT),
            new Checkpoint(4, 8, Direction.UP),
            new Checkpoint(4, 7, Direction.RIGHT),
            new Checkpoint(7, 7, Direction.DOWN),
            new Checkpoint(7,10,Direction.NONE)
    },
            new Tile[][] {
                    {Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND,},
                    {Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.PATH},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND,Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND},
                    {Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND}
            }),
    MAP2("map 2",
            new Checkpoint[]{
                    new Checkpoint(5, 6, Direction.RIGHT),
                    new Checkpoint(6, 6, Direction.UP),
                    new Checkpoint(6, 3, Direction.LEFT),
                    new Checkpoint(3, 3, Direction.DOWN),
                    new Checkpoint(3, 8, Direction.RIGHT),
                    new Checkpoint(8, 8, Direction.UP),
                    new Checkpoint(8, 1, Direction.LEFT),
                    new Checkpoint(1, 1, Direction.DOWN),
                    new Checkpoint(1,10,Direction.NONE)
    },
            new Tile[][] {
                    {Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND,},
                    {Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND,Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.GROUND},
                    {Tile.GROUND, Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND}
            }),
    MAP3("map 3",
            new Checkpoint[]{
            new Checkpoint(0, 8, Direction.RIGHT),
            new Checkpoint(9, 8, Direction.UP),
            new Checkpoint(9, 4, Direction.LEFT),
            new Checkpoint(0, 4, Direction.UP),
            new Checkpoint(0, 1, Direction.RIGHT),
            new Checkpoint(10, 1, Direction.NONE),
    },
            new Tile[][] {
                    {Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND,},
                    	{Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH},
                    	{Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND},
                    	{Tile.PATH, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND},
                    	{Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH,Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH},
                    	{Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH},
                    	{Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH},
                    	{Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.PATH},
                    	{Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH, Tile.PATH},
                    	{Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND, Tile.GROUND}
            }
);
    private final String name;
    private final Checkpoint[] checkpoint;
    private final Tile[][] tile;

    Map(String name, Checkpoint[] checkpoint, Tile[][] tile) {
        this.checkpoint = checkpoint;
        this.tile = tile;
        this.name = name;
    }

    public Checkpoint getCheckpoint(int i) {
        return checkpoint[i];
    }

    public Tile getTile(int y, int x) {
        return tile[y][x];
    }

    public int getHeight(){
        return tile.length;
    }

    public int getWidth(){
        return tile[0].length;
    }

    @Override
    public String toString() {
        return name;
    }
}
