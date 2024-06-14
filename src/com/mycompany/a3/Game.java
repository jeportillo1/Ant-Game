package com.mycompany.a3;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*; 

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private int time = 20;
	private Vector<ICollider> curObjColli = new Vector<ICollider>();
	private UITimer timer;
	private Button accelButton;
	private Button leftButton;
	private Button brakeButton;
	private Button rightButton;
	private BrakeCommand brake;
	private RightCommand right;
	private LeftCommand left;
	private AccelCommand accel;
	private PositionCommand position;
	private Button positionButton;
	private Button mode;
	private PlayPauseCommand playPause;
	private SoundCommand s;
	private CheckBox sound;
	private Toolbar myToolbar; 
	
	//Game constructor that also creates  a game world and initializes it
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw.getMyGame(), gw);
		sv = new ScoreView(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		myToolbar = new Toolbar();
		setToolbar(myToolbar);
		myToolbar.setTitle("ThePath Game");
		
		accel = new AccelCommand(gw);
		myToolbar.addCommandToSideMenu(accel);
		
		this.keyReleased(97);
		this.addKeyListener(97,accel);
		
		sound = new CheckBox("Sound");//set the style of the check box
		sound.getAllStyles().setBgTransparency(255);
		sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);//add the CheckBox component as a side menu item
		s = new SoundCommand();
		s.setGameWorld(gw);
		sound.setCommand(s);
		myToolbar.addComponentToSideMenu(sound);
		
		AboutCommand about = new AboutCommand();
		myToolbar.addCommandToSideMenu(about);
		
		HelpCommand help = new HelpCommand();
		myToolbar.addCommandToSideMenu(help);
		
		ExitCommand exit = new ExitCommand(gw);
		myToolbar.addCommandToSideMenu(exit);
		
		HelpCommand help2 = new HelpCommand();
		myToolbar.addCommandToRightBar(help2);
		
		setLayout(new BorderLayout());
		
		Container topContainer = new Container(new FlowLayout(Component.CENTER));
		topContainer.add(sv.myTimeLabel());
		topContainer.add(sv.myLifeLabel());
		topContainer.add(sv.myAntLabel());
		topContainer.add(sv.mySoundLabel());
		topContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		this.revalidate();
		
		add(BorderLayout.NORTH,topContainer);
		
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		accelButton = new Button("Accelerate");
		
		
		accelButton.addActionListener(accel);
		accelButton.getUnselectedStyle().setBgTransparency(255);
		accelButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		accelButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		accelButton.getAllStyles().setPadding(Component.TOP, 6);
		accelButton.getAllStyles().setPadding(Component.BOTTOM, 6);
		accelButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		accelButton.getDisabledStyle().setBgTransparency(255);
		accelButton.getDisabledStyle().setBgColor(ColorUtil.BLACK);
		accelButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		
		leftButton = new Button("Left");
		left = new LeftCommand(gw);
		
		this.keyReleased(108);
		this.addKeyListener(108,left);
		
		leftButton.addActionListener(left);
		leftButton.getUnselectedStyle().setBgTransparency(255);
		leftButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setPadding(Component.TOP, 6);
		leftButton.getAllStyles().setPadding(Component.BOTTOM, 6);
		leftButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		leftButton.getDisabledStyle().setBgTransparency(255);
		leftButton.getDisabledStyle().setBgColor(ColorUtil.BLACK);
		leftButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		
		leftContainer.getAllStyles().setPadding(Component.TOP, 40);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		leftContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		leftContainer.add(accelButton);
		leftContainer.add(leftButton);
		
		add(BorderLayout.WEST,leftContainer);
		
		
		
		add(BorderLayout.CENTER,mv);
		timer = new UITimer(this);
		timer.schedule(time, true, this);
		
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		
		mode = new Button("Pause");
		playPause = new PlayPauseCommand(this,mode,time,gw,true);
		mode.addActionListener(playPause);
		
		mode.getUnselectedStyle().setBgTransparency(255);
		mode.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		mode.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		mode.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		mode.getSelectedStyle().setBgTransparency(255);
		mode.getSelectedStyle().setBgColor(ColorUtil.WHITE);
		mode.getSelectedStyle().setFgColor(ColorUtil.BLUE);
		mode.getSelectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		mode.getAllStyles().setPadding(Component.TOP, 6);
		mode.getAllStyles().setPadding(Component.BOTTOM, 6);
		
		positionButton = new Button("Position");
		position = new PositionCommand(mv, gw);
		
		positionButton.addActionListener(position);
		
		positionButton.getUnselectedStyle().setBgTransparency(255);
		positionButton.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		positionButton.getUnselectedStyle().setFgColor(ColorUtil.BLACK);
		positionButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		positionButton.getSelectedStyle().setBgTransparency(255);
		positionButton.getSelectedStyle().setBgColor(ColorUtil.CYAN);
		positionButton.getSelectedStyle().setFgColor(ColorUtil.WHITE);
		positionButton.getSelectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		positionButton.getDisabledStyle().setBgTransparency(255);
		positionButton.getDisabledStyle().setBgColor(ColorUtil.BLACK);
		positionButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		
		positionButton.getAllStyles().setPadding(Component.TOP, 6);
		positionButton.getAllStyles().setPadding(Component.BOTTOM, 6);
		
		positionButton.setEnabled(false);
		
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		bottomContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		bottomContainer.add(positionButton);
		bottomContainer.add(mode);
		
		add(BorderLayout.SOUTH,bottomContainer);
		
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		brakeButton = new Button("Brake");
		brake = new BrakeCommand(gw);
		
		this.keyReleased(98);
		this.addKeyListener(98,brake);
		
		brakeButton.addActionListener(brake);
		brakeButton.getUnselectedStyle().setBgTransparency(255);
		brakeButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setPadding(Component.TOP, 6);
		brakeButton.getAllStyles().setPadding(Component.BOTTOM, 6);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		brakeButton.getDisabledStyle().setBgTransparency(255);
		brakeButton.getDisabledStyle().setBgColor(ColorUtil.BLACK);
		brakeButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		
		
		rightButton = new Button("Right");
		right = new RightCommand(gw);
		
		this.keyReleased(114);
		this.addKeyListener(114,right);
		
		rightButton.addActionListener(right);
		rightButton.getUnselectedStyle().setBgTransparency(255);
		rightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		rightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setPadding(Component.TOP, 6);
		rightButton.getAllStyles().setPadding(Component.BOTTOM, 6);
		rightButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		rightButton.getDisabledStyle().setBgTransparency(255);
		rightButton.getDisabledStyle().setBgColor(ColorUtil.BLACK);
		rightButton.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		
		
		rightContainer.getAllStyles().setPadding(Component.TOP, 40);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		rightContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		rightContainer.add(brakeButton);
		rightContainer.add(rightButton);
		
		add(BorderLayout.EAST,rightContainer);
		
		MapCommand map = new MapCommand(gw);
		this.keyReleased(109);
		this.addKeyListener(109,map);
		
		this.show();
		
		gw.setxVal(mv.getWidth());
		gw.setyVal(mv.getHeight());
		
		gw.init();
		gw.createSounds();
		this.revalidate();
		
	}
	
	public UITimer getTimer() {
		return timer;
	}
	
	public Button getAccelButton() {
		return accelButton;
	}
	
	public Button getLeftButton() {
		return leftButton;
	}
	
	public Button getRightButton() {
		return rightButton;
	}
	
	public Button getBrakeButton() {
		return brakeButton;
	}
	
	public CheckBox getSoundBox() {
		return sound;
	}
	
	public Button getPositionButton() {
		return positionButton;
	}
	
	public Toolbar getToolBar() {
		return myToolbar;
	}
	
	public AccelCommand getAccelCommand() {
		return accel;
	}
	
	public BrakeCommand getBrakeCommand() {
		return brake;
	}
	
	public LeftCommand getLeftCommand() {
		return left;
	}
	
	public RightCommand getRightCommand() {
		return right;
	}
	
	public PlayPauseCommand getPlPaCommand() {
		return playPause;
	}
	
	public SoundCommand getSCommand() {
		return s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.tickA1(time);
		ICollider a1 = null;
		ICollider a2 = null;
		
		IIterator iter = gw.getMyGame().getIterator(); 
		// check if moving caused any collisions
		iter = gw.getMyGame().getIterator();
		while(iter.hasNext()) {
			ICollider curObj = (ICollider)iter.getNext(); 
			// get a collidable object
			// check if this object collides with any OTHER object
			IIterator iter2 = gw.getMyGame().getIterator();
			while(iter2.hasNext()) {
				ICollider otherObj = (ICollider) iter2.getNext(); // get a collidable object
				// check for collision
				if(otherObj!= curObj) {
				// make sure it's not the SAME object
					
					if(curObj.collidesWith((GameObjects) otherObj)) {
						if(a1 != curObj && a2 != otherObj) {
							if(curObjColli.indexOf(otherObj) == -1) {
								curObj.handleCollision((GameObjects) otherObj, gw);	
								curObjColli.add(otherObj);
								a1 = curObj;
								a2 = otherObj;
							}
						}
						else if(a1 == curObj && a2 == otherObj){
							curObjColli.clear();
						}
					}
				}
			
			}
		}	
		mv.repaint();
		
		}
		
	}
