package com.github.achaaab.tetris.model;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record Direction(int dx, int dy, int dr) {

	public static final Direction DOWN = new Direction(0, 1, 0);
	public static final Direction LEFT = new Direction(-1, 0, 0);
	public static final Direction RIGHT = new Direction(1, 0, 0);
	public static final Direction CLOCKWISE = new Direction(0, 0, 1);
	public static final Direction COUNTERCLOCKWISE = new Direction(0, 0, -1);

	/**
	 * @param direction
	 * @return combinaison des 2 directions (this et direction)
	 * @since 0.0.0
	 */
	public Direction combine(Direction direction) {

		return new Direction(
				dx + direction.dx,
				dy + direction.dy,
				dr + direction.dr);
	}
}
