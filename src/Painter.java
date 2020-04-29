//https://javahungry.blogspot.com/2019/12/java-projects-for-beginners.html
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;


public class Painter implements ActionListener, MouseListener, KeyListener
{
	public static Painter painter;
	public static Renderer renderer;
	public static boolean started;
	public static final int WIDTH = 1200, HEIGHT = 600, TRACK_WIDTH = 100;
	public Rectangle car;
	public static Rectangle[] cars=new Rectangle[4];
	public static int numCar=0;
	public Image singer, bg;
	public Long currentFrame; 
	public Clip clip;  
	public AudioInputStream audioInputStream; 
	public String music="off";
	public static RaceTrack track;


	public Painter()
	{	
		started=false;
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		renderer = new Renderer();
		jframe.add(renderer);
		jframe.setTitle("Race Car");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);
		timer.start();
	}
	
	
	public static void main(String[] args) {
		track = new RaceTrack();
		RaceTrack track = new RaceTrack();
		RaceCar[] allRaceCars = SimulationDriver.getSomeRaceCars();
		FormulaOne[] allFormulaOnes = SimulationDriver.getSomeFormulaOnes();
		SportsCar[] allSportsCars = SimulationDriver.getSomeSportsCars();
		int numCars=SimulationDriver.numRaceCars+SimulationDriver.numFormulaOnes+SimulationDriver.numSportsCars;
		Car[] allCars= new Car[numCars];
		for(int i=0;i<SimulationDriver.numRaceCars;i++) 
			allCars[i] = allRaceCars[i];
		for(int i=0;i<SimulationDriver.numFormulaOnes;i++) 
			allCars[i+SimulationDriver.numRaceCars] = allFormulaOnes[i];
		for(int i=0;i<SimulationDriver.numSportsCars;i++) 
			allCars[i+SimulationDriver.numRaceCars+SimulationDriver.numFormulaOnes] = allSportsCars[i];
		track.setCars(allCars);
		painter=new Painter();
		createCar(numCars);
	}
	
	
	public static void createCar(int num){
		num= (num>4)?4:num;
		for(int i=0;i<num;i++){
			numCar++;
			Rectangle newcar = new Rectangle(0, TRACK_WIDTH*numCar+150, 100, 40);
			cars[i]=newcar;
		}
	}
	
	
	public static void update(){
		for(int i=0;i<numCar;i++){
			if (cars[i]!=null){
				cars[i].x=RaceTrack.cars[i].unit*1200/100;
			}
		}
		renderer.repaint(); 
	}
	

	public void repaint(Graphics g)
	{
		try {
			bg = ImageIO.read(new File("bg.jpg"));
		}
		catch(IOException e) {e.printStackTrace();}
		g.drawImage(bg, 0,0,1200,200, null);
		try {
			singer = ImageIO.read(new File("singer.jpg"));
		}
		catch(IOException e) {e.printStackTrace();}
		g.drawImage(singer, 50,30,150,150, null);
		
		g.setColor(Color.orange);
		g.fillRect(0, 200, WIDTH, TRACK_WIDTH);
		g.setColor(Color.red);
		g.fillRect(0, 200+TRACK_WIDTH, WIDTH, TRACK_WIDTH);
		g.setColor(Color.red.darker());
		g.fillRect(0, 200+TRACK_WIDTH*2, WIDTH, TRACK_WIDTH);
		g.setColor(Color.red.darker().darker());
		g.fillRect(0, 200+TRACK_WIDTH*3, WIDTH, TRACK_WIDTH);
		g.setColor(Color.green);
		g.fillRect(900, 200, 40, 80);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 20, 20));
		g.drawString("Pitstop",895, 245);
		
		Color blue=Color.cyan;
		for(int i=0;i<numCar;i++){
			g.setColor(blue);
			blue=blue.darker();
			if (cars[i]!=null) {
				g.fillRect(cars[i].x, cars[i].y, cars[i].width, cars[i].height);
			}
		}

		if (!started)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial", 100, 100));
			g.drawString("PRESS UR SPACEBAR!!!", 10, HEIGHT/2);
		}
		else{
			g.setColor(Color.pink);
			g.setFont(new Font("Arial", 20, 20));
			g.drawString(RaceTrack.out,210, 100);
			g.setFont(new Font("Arial", 20, 15));
			g.drawString(RaceTrack.out_2,210, 150);
		}
		
		if(RaceTrack.crashed==true){
			try {
				playSound("bang.wav");
			} catch (Exception e) {}
		}
	}
	
	
	public void playSound(String filename)throws UnsupportedAudioFileException,IOException, LineUnavailableException{ 
		audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile()); 
		clip = AudioSystem.getClip(); 
		clip.open(audioInputStream); 
		clip.start();
	} 


	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{

			if(!started){
				started=true;
			}
			if(!FinishLine.finished()){
				track.tick();
				for(int i=0;i<numCar;i++){
					if(RaceTrack.cars[i]==null){
						cars[i]=null;
					}
				}
				Painter.update();
			}
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e){
		if(e.getX()<200&&e.getY()<200&e.getX()>50&&e.getY()>50){
			if (music=="off"){
				try {
					playSound("HuaKui.wav");
					music="play";
				} catch (Exception ex) {}
			}
			else if (music=="play") {
				this.currentFrame=this.clip.getMicrosecondPosition(); 
				clip.stop(); 
				music="pause";
			}
			else if (music=="pause"){
				try {
					clip.close(); 
					audioInputStream = AudioSystem.getAudioInputStream(new File("HuaKui.wav").getAbsoluteFile()); 
					clip.open(audioInputStream); 
					clip.setMicrosecondPosition(currentFrame); 
					clip.start(); 
					music="play"; 
				} catch (Exception ex) {}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e){}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyPressed(KeyEvent e){}
}