import java.util.LinkedList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

public class TicTacToe{
    public static void main(String[]args) throws IOException{
        String[]input=new String[0];
        Scanner in=null;
        in=new Scanner(new File(args[0]));
        makeScreen makeScreen=new makeScreen();
        makeScreen.setVisible(true);
        makeScreen.Reader(in,input);
    }
}
class Game{
String playerName;
Player play;
Battle battle;
Creature creature;
    Game(){

    }
    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName=playerName;
    }

    public Player getPlayer(){
        return play;
    }
    public void Battle(Player player, String creature){
        switch(creature){
            case "wolf":
            this.creature=new Wolf("Wolf",10,3,0,1);
            default:
            this.creature=new Wolf("Wolf",10,3,0,1);
        }
        battle=new Battle(player, this.creature);
        battle.Attacking();
        battle.Attacked();
    }
}

class Battle{

    Game game;
    Player player;
    Creature creature;
    public Battle(Player player,Creature creature){
        this.player=player;
        this.creature=creature;
        
    }
    public void Attacking(){
       creature.setHealth(creature.getHealth()-player.getAttack());
    }
    public void Attacked(){
        this.player.setHealth(player.getHealth()-creature.getAttack());
    }

}

class Moves{
    
}

class Creature{
    private String name;
    private int health, attack, mp,level;
    private String type;
    private boolean alive=true;


   public Creature(String name,int health,int attack,int mp,int level){
       this.name=name;
       this.health=health;
       this.attack=attack;
       this.mp=mp;
       this.level=level;
   }

   public String getName(){
       return this.name;
   }
   public int getHealth(){
       return health;
   }
   public void setHealth(int health){
       this.health=health;
   }
   public int getAttack(){
       return this.attack;
   }

   public void setAttack(int attack){
       this.attack=attack;
   }
   public void setmp(int mp){
       this.mp=mp;
   }
   public void setLevel(int level){
       this.level=level;
   }
   public int getLevel(){
       return level;
   }
   public boolean isAlive(){
       return alive;
   }
   public void isDead(){
       if(health<=0){
           alive=false;
       }
       
   }
}

class Player extends Creature{
   private int experience;
   int level;
   String[]moves;
   private boolean Archer,Warrior,Alchemist=false;
   private boolean alive=true;
   public Player(String name, int health, int attack, int mp, int level,int experience){
       super(name,health,attack,mp,level);
       this.experience=experience;
   }
   public int getExperience(){
       return experience;
   }
   public void setExperience(int experience){
       this.experience=experience;
   }

   private int moveData(String move){
       switch(move){
           case "Basic Attack":
           return 3;
           default:
           return 0;

       }
   }
}

class Wolf extends Creature{
   public Wolf(String name, int health, int attack, int mp, int level){
       super(name,health,attack,mp,level);
   }
}


class makeScreen extends JFrame{
    JButton play = new JButton("Play");
    JButton exit = new JButton("Exit");
    JButton exit2 = new JButton("Exit");
    JButton settings = new JButton("Settings");
    JButton mainMenu = new JButton("Main Menu");
    JButton next = new JButton("Next");
    JButton att1 = new JButton("Basic Attack");
    CardLayout layout = new CardLayout();
    JPanel panel = new JPanel();
    JPanel game = new JPanel();
    JPanel menu = new JPanel();
    JPanel battleMenu=new JPanel();
    Border border = BorderFactory.createLineBorder(Color.BLACK,2);
    JTextField gameText=new JTextField();
    String[]updateText;
    Scanner scanner=null;
    Game RPG =new Game();
    boolean battle=false;
    Player player = new Player("Bob",100,3,20,1,0);
    String tempMonster;
 
    makeScreen(){
        super();
        this.setSize(400,400);
        this.setTitle("The Game");
        panel.setLayout(layout);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addButtons();
        gameText.setText("This is a game loool");
        gameText.setBounds(50,50,300,300);
        gameText.setEditable(false);


    }
    private void addButtons(){
        play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel,"Game");
			}
		});
        settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel,"Game");
			}
		});
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.exit(0);
			}
        });
        exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.exit(0);
			}
		});
        mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel,"Menu");
			}
        });
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Reader(scanner,updateText);
                
            }
        });
        menu.add(play);
        menu.add(exit);
        menu.add(settings);

        game.add(mainMenu);
        game.add(next);
        game.add(exit2);
        game.add(gameText);

        battleMenu.add(att1);


        game.setBackground(Color.DARK_GRAY);
        menu.setBackground(Color.DARK_GRAY);
        battleMenu.setBackground(Color.DARK_GRAY);

        panel.add(menu,"Menu");
        panel.add(game,"Game");
        panel.add(battleMenu,"Battle Menu");

        add(panel);
        layout.show(panel,"Menu");
    }
    public void changeText(String[]input){
        String temp="";
        for(int i=0;i<input.length;i++){
            temp+=input[i];
            temp+=" ";
            temp+="\n";
        }
        gameText.setText(temp);
        System.out.println(temp);
        
    }
    public void Reader(Scanner in,String[]input){
        att1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                RPG.Battle(player,tempMonster);
            }
        });

        if(scanner==null){
            setScanner(in);
        }
        String length=scanner.nextLine();
        input=new String[Integer.parseInt(length)];
        for(int i=0;i<Integer.parseInt(length);i++){
            input[i]=scanner.nextLine();
            String boo=Character.toString(input[i].charAt(0));
            System.out.println(boo);
            if(boo.equals("#")){
                String monster=input[i].substring(1);
                System.out.println(monster+"------");
                tempMonster=monster;
                battle=true; 
                System.out.println(battle);   
                if(battle==true){
                    layout.show(panel,"Battle Menu");
                }   
            }
        }
        changeText(input);   
    }
    private void setScanner(Scanner in){
        this.scanner=in;
    }
    
}




