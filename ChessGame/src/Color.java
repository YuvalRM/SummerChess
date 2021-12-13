
public enum Color {
	Black {public Color opposite() {
		return Color.White;
	}},
	White{public Color opposite() {
		return Color.Black;
	}},
	Nothing{public Color opposite() {
		return Color.Nothing;
	}};
	public abstract Color opposite();

}
