package edu.pacific.comp55.starter;
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "Sounds";
	private static final String[] SOUND_FILES = { "Track0.wav", "Track1.wav" };

	private SomePane gamePane;
	private MenuPane menu;
	private int count;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		gamePane = new SomePane(this);
		
		menu = new MenuPane(this);
		setupInteractions();
		switchToMenu();
		
	}

	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToSome() {
		switchToScreen(gamePane);
	}

	private void playRandomSound() {
		//AudioPlayer audio = AudioPlayer.getInstance();
		//audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}

}
