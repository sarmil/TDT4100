package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import application.Brikker;
import application.SampleLevels;
import application.Brett;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import application.Brett;
import java.util.Scanner;

import application.Sokoban;


public class Main extends Application {
	Brett brettet;
	int hoyde,bredde;
	public void start2(String lvl) {
		try {
			Stage primaryStage = new Stage();
			String board = lvl;
			this.brettet = new Brett(board);
			String[] temp = board.split("\n");
			hoyde = temp.length;
			bredde = temp[0].length(); //Antar at banen er en perfekt firkant.
			Brikker[][] brett = new Brikker[hoyde][bredde];
			GridPane root = new GridPane();
			Scene scene = new Scene(root,bredde*16,hoyde*16);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Simen presenterer: Sokoban!");
			primaryStage.setScene(scene);
			primaryStage.show();

			for(int i=0;i<temp.length;i++){
				for(int j=0;j<temp[i].length();j++){
					brett[i][j] = new Brikker(temp[i].charAt(j));
					if(brett[i][j].hentbrikke()=='#'){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\wall16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else if(brett[i][j].hentbrikke()=='.'){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\target16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else if(brett[i][j].hentbrikke() == '*'){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\box_on_target16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else if(brett[i][j].hentbrikke() == '@'){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\player16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else if(brett[i][j].hentbrikke() == '+'){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\player_on_target16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else if(brett[i][j].hentbrikke() == ' '){
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\empty16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
					else{
						Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\box16x16.png");
						root.add(new ImageView(brikken), j, i);
					}
				}
			}
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

				@Override
				public void handle(KeyEvent event) {
					try {
						if(event.getCode()==KeyCode.W){
							brettet.opp();
							oppdater(root);
							if(ferdig()){
								TextFlow a = new TextFlow(new Text("Du vant! Wohoo! g.g."));
								Scene test = new Scene(a,100,100);
								primaryStage.setScene(test);
								
							}
						}
						else if(event.getCode()==KeyCode.A){
							brettet.venstre();
							oppdater(root);
							if(ferdig()){
								TextFlow a = new TextFlow(new Text("Du vant! Wohoo! g.g."));
								Scene test = new Scene(a,100,100);
								primaryStage.setScene(test);
							}
						}
						else if(event.getCode() == KeyCode.S){
							brettet.ned();
							oppdater(root);
							if(ferdig()){
								TextFlow a = new TextFlow(new Text("Du vant! Wohoo! g.g."));
								Scene test = new Scene(a,100,100);
								primaryStage.setScene(test);
							}
						}
						else if(event.getCode() == KeyCode.D){
							brettet.hoyre();
							oppdater(root);
							if(ferdig()){
								TextFlow a = new TextFlow(new Text("Du vant! Wohoo! g.g."));
								Scene test = new Scene(a,100,100);
								primaryStage.setScene(test);
							}
						}
					}
					catch(IllegalArgumentException e){
						System.out.println(e.getMessage());
					}
					
				}});
				
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("test");
		}
		
	}
	
//	public String nivaa(){
//		int nivaa;
//		boolean init = false;
//		Scanner skanner = new Scanner(System.in);
//		while(!init){
//			System.out.println("Hvilket nivå ønker du å spille på? Velg fra 1 til-og-med 4.");
//			System.out.print("> ");
//			nivaa = skanner.nextInt();
//			if(nivaa == 1){
//				init = true;
//				return SampleLevels.SAMPLE_LEVEL1;
//			}
//			else if(nivaa == 2){
//				init = true;
//				return SampleLevels.SAMPLE_LEVEL2;
//			}
//			else if(nivaa == 3){
//				init = true;
//				return SampleLevels.SAMPLE_LEVEL3;
//			}
//			else if(nivaa == 4){
//				init = true;
//				return SampleLevels.SAMPLE_LEVEL4;
//			}
//			else{
//				System.out.println("Du må velge et nivå mellom 1 og 4.");
//			}
//		}
//		skanner.close();
//		return null;
//	}
//	
	
	public void start(Stage primaryStage){
		Button level1 = new Button();
		level1.setText("Nivå 1");
		level1.setLayoutX(100);
		level1.setLayoutY(50);
		
		Button level2 = new Button();
		level2.setText("Nivå 2");
		level2.setLayoutX(160);
		level2.setLayoutY(50);
		
		Button level3 = new Button();
		level3.setText("Nivå 3");
		level3.setLayoutX(220);
		level3.setLayoutY(50);
		
		Button level4 = new Button();
		level4.setText("Nivå 4");
		level4.setLayoutX(280);
		level4.setLayoutY(50);
		
		level1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				start2(SampleLevels.SAMPLE_LEVEL1);
			}
		});
		
		level2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				start2(SampleLevels.SAMPLE_LEVEL2);
			}
		});
		
		level3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				start2(SampleLevels.SAMPLE_LEVEL3);
			}
		});
		
		level4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				start2(SampleLevels.SAMPLE_LEVEL4);
			}
		});
		
		
		Pane root2 = new Pane();
		root2.getChildren().add(level1);
		root2.getChildren().add(level2);
		root2.getChildren().add(level3);
		root2.getChildren().add(level4);
		
		Stage secondaryStage = new Stage();
		secondaryStage.setTitle("Simen presenterer: Sokoban!");
		secondaryStage.setScene(new Scene(root2,400,100));
		secondaryStage.show();
	}
	

	public void oppdater(GridPane rooten){
		Brikker[][] temp = this.brettet.brett;
		for(int i=0;i<this.hoyde;i++){
			for(int j=0;j<this.bredde;j++){
				if(temp[i][j].hentbrikke()=='#'){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\wall16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else if(temp[i][j].hentbrikke()=='.'){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\target16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else if(temp[i][j].hentbrikke() == '*'){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\box_on_target16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else if(temp[i][j].hentbrikke() == '@'){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\player16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else if(temp[i][j].hentbrikke() == '+'){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\player_on_target16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else if(temp[i][j].hentbrikke() == ' '){
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\empty16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
				else{
					Image brikken = new Image("file:///C:\\Users\\Simen\\workspace\\Sokoban\\src\\application\\bilder\\box16x16.png");
					rooten.add(new ImageView(brikken), j, i);
				}
			}
		}
	}
	
	public boolean ferdig(){
		return this.brettet.ferdig();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
