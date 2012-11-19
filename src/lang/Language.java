package lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Language {
	public final String Player;
	public final String One;
	public final String Two;
	public final String Game;
	public final String Settings;
	public final String About;
	public final String Time;
	public final String NewGame;
	public final String Quit;
	public final String Preferences;
	public final String Credits;
	public final String Color;
	public final String Red;
	public final String Orange;
	public final String Yellow;
	public final String Green;
	public final String Blue;
	public final String Violet;
	public final String Human;
	public final String Computer;
	public final String Easy;
	public final String Hard;
	public final String Language;
	public final String Finish;
	public final String Cancel;
	public final String Win;
	public final String Lose;
	public final String Draw;
	public final String Delay;

	public Language(final String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("src/lang/"+fileName));
		Player = reader.readLine();
		One = reader.readLine();
		Two = reader.readLine();
		Game = reader.readLine();
		Settings = reader.readLine();
		About = reader.readLine();
		Time = reader.readLine();
		NewGame = reader.readLine();
		Quit = reader.readLine();
		Preferences = reader.readLine();
		Credits = reader.readLine();
		Color = reader.readLine();
		Red = reader.readLine();
		Orange = reader.readLine();
		Yellow = reader.readLine();
		Green = reader.readLine();
		Blue = reader.readLine();
		Violet = reader.readLine();
		Human = reader.readLine();
		Computer = reader.readLine();
		Easy = reader.readLine();
		Hard = reader.readLine();
		Language = reader.readLine();
		Finish = reader.readLine();
		Cancel = reader.readLine();
		Win = reader.readLine();
		Lose = reader.readLine();
		Draw = reader.readLine();
		Delay = reader.readLine();

		reader.close();
	}
}
