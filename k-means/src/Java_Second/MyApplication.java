package Java_Second;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyApplication extends JFrame{
	StateManager stateManager;
	MyCanvas canvas;
	private int x;
	private int y;
	private int btn;
	
	private JMenuBar menuBar;
	private JMenu colorMenu, colorLMenu, widthMenu, shadowMenu,fileMenu,windowMenu,pageMenu,csvMenu,clusteringMenu,URMenu;
	private JMenuItem redItem, blueItem, greenItem,transparentItem,blackLItem, redLItem, blueLItem, greenLItem, oneItem, twoItem, threeItem, shadowOnItem,shadowOffItem,saveItem,loadItem,screenItem,csvloadItem,csvsaveItem,clustering2Item,clustering3Item,clustering4Item;
	private JMenuItem undoItem,redoItem;
	private JMenuItem[] pageItems;
	Mediator med;
	PCA pca;
	
	public MyApplication() {
		super("My Paint Application");
		
		canvas = new MyCanvas();
		med = canvas.getMediator();
		med.Stackpage();
		canvas.setBackground(Color.white);
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(canvas,med);
		pca = new PCA(med);
		
		
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		HendcagonalButton hendcagonalButton = new HendcagonalButton(stateManager);
		jp.add(hendcagonalButton);
		PolygonButton polygonButton = new PolygonButton(stateManager);
		jp.add(polygonButton);
		DropCheck dropCheck = new DropCheck(stateManager);
		jp.add(dropCheck);
		DashCheck dashCheck = new DashCheck(stateManager);
		jp.add(dashCheck);
		DashlengthCheck dashlengthCheck = new DashlengthCheck(stateManager);
		jp.add(dashlengthCheck);
		JLabel line = new JLabel("line:");
		jp.add(line);
		NlineComboBox nlineComboBox = new NlineComboBox(stateManager);
		jp.add(nlineComboBox);
		JLabel width = new JLabel("width:");
		jp.add(width);
		WidthlineComboBox widthlineComboBox = new WidthlineComboBox(stateManager);
		jp.add(widthlineComboBox);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp,BorderLayout.NORTH);
		getContentPane().add(canvas,BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		colorMenu = new JMenu("Color");
		colorLMenu = new JMenu("LineColor");
		widthMenu = new JMenu("Width");
		shadowMenu = new JMenu("Shadow");
		fileMenu = new JMenu("file");
		windowMenu = new JMenu("window");
		pageMenu = new JMenu("page");
		csvMenu = new JMenu("csv");
		clusteringMenu = new JMenu("clustering");
		URMenu = new JMenu("UR");
		redItem = new JMenuItem("Red");
		blueItem = new JMenuItem("Blue");
		greenItem = new JMenuItem("Green");
		transparentItem = new JMenuItem("Transparent");
		blackLItem = new JMenuItem("Black");
		redLItem = new JMenuItem("Red");
		blueLItem = new JMenuItem("Blue");
		greenLItem = new JMenuItem("Green");
		oneItem = new JMenuItem("One");
		twoItem = new JMenuItem("Two");
		threeItem = new JMenuItem("Three");
		shadowOnItem = new JMenuItem("On");
		shadowOffItem = new JMenuItem("Off");
		saveItem = new JMenuItem("save");
		loadItem = new JMenuItem("load");
		screenItem = new JMenuItem("screen");
		csvloadItem = new JMenuItem("load");
		csvsaveItem = new JMenuItem("save");
		clustering2Item = new JMenuItem("2");
		clustering3Item = new JMenuItem("3");
		clustering4Item = new JMenuItem("4");
		undoItem = new JMenuItem("undo");
		redoItem = new JMenuItem("redo");
		colorMenu.add(redItem);
		colorMenu.add(blueItem);
		colorMenu.add(greenItem);
		colorMenu.add(transparentItem);
		colorLMenu.add(redLItem);
		colorLMenu.add(blueLItem);
		colorLMenu.add(greenLItem);
		colorLMenu.add(blackLItem);
		widthMenu.add(oneItem);
		widthMenu.add(twoItem);
		widthMenu.add(threeItem);
		shadowMenu.add(shadowOnItem);
		shadowMenu.add(shadowOffItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		windowMenu.add(screenItem);
		csvMenu.add(csvloadItem);
		csvMenu.add(csvsaveItem);
		clusteringMenu.add(clustering2Item);
		clusteringMenu.add(clustering3Item);
		clusteringMenu.add(clustering4Item);
		URMenu.add(redoItem);
		URMenu.add(undoItem);
		
		
		redItem.addActionListener(new ColorActionListener(med, Color.red, canvas));
		blueItem.addActionListener(new ColorActionListener(med, Color.blue, canvas));
		greenItem.addActionListener(new ColorActionListener(med, Color.green, canvas));
		transparentItem.addActionListener(new ColorActionListener(med, new Color(0,0,0,0), canvas));
		redLItem.addActionListener(new LineActionListener(med, Color.red, canvas));
		blueLItem.addActionListener(new LineActionListener(med, Color.blue, canvas));
		greenLItem.addActionListener(new LineActionListener(med, Color.green, canvas));
		blackLItem.addActionListener(new LineActionListener(med, Color.black, canvas));
		oneItem.addActionListener(new WidthActionListener(med, 1, canvas));
		twoItem.addActionListener(new WidthActionListener(med, 2, canvas));
		threeItem.addActionListener(new WidthActionListener(med, 3, canvas));
		shadowOnItem.addActionListener(new ShadowActionListener(med, true, canvas));
		shadowOffItem.addActionListener(new ShadowActionListener(med, false, canvas));
		saveItem.addActionListener(new SaveActionListener(med,canvas));
		loadItem.addActionListener(new LoadActionListener(med,canvas));
		screenItem.addActionListener(new FullscreenActionListener(this));
		csvloadItem.addActionListener(new CSVloadActionListener(pca, canvas));
		csvsaveItem.addActionListener(new CSVsaveActionListener(pca, canvas));
		clustering2Item.addActionListener(new ClusteringActionListener(pca, canvas, 2));
		clustering3Item.addActionListener(new ClusteringActionListener(pca, canvas, 3));
		clustering4Item.addActionListener(new ClusteringActionListener(pca, canvas, 4));
		redoItem.addActionListener(new RedoActionListener(med, canvas));
		undoItem.addActionListener(new UndoActionListener(med, canvas));
		
		menuBar.add(colorMenu);
		menuBar.add(shadowMenu);
		menuBar.add(colorLMenu);
		menuBar.add(widthMenu);
		menuBar.add(fileMenu);
		menuBar.add(pageMenu);
		menuBar.add(windowMenu);
		menuBar.add(csvMenu);
		menuBar.add(clusteringMenu);
		menuBar.add(URMenu);
		
		pageItems = new JMenuItem[10];
        for (int i = 0; i < 10; i++) {
            pageItems[i] = new JMenuItem("Page " + (i + 1));
            pageItems[i].addActionListener(new PageActionListener(med, canvas, i));
            pageMenu.add(pageItems[i]);
        }
        
		canvas.setFocusable(true);
		
		
		
		canvas.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					btn = e.getButton();
					if(btn == MouseEvent.BUTTON1) {
						canvas.requestFocusInWindow();
						if(!stateManager.modeget()) {
							stateManager.mouseDown(e.getX(), e.getY());
							x = e.getX();
							y = e.getY();
							System.out.println(getSize());
							canvas.repaint();
						}else {
							if(med.setSelected(e.getX(),e.getY())) {
								med.addRectangle(e.getX(), e.getY());
								x = e.getX();
								y = e.getY();
							}else {
								x = e.getX();
								y = e.getY();
							}
							canvas.repaint();
						}
					}
					if(btn == MouseEvent.BUTTON3) {
						canvas.getMediator().paste(e.getX(),e.getY());
					}
				}
				public void mouseReleased(MouseEvent e) {
					canvas.requestFocusInWindow();
					if(btn == MouseEvent.BUTTON1) {
						if(!stateManager.modeget()) {
							stateManager.mouseUp(x-e.getX(), y-e.getY());
							canvas.repaint();
						}else {
							if(med.getRectangle() != null) {
								med.setrectselect();
								canvas.repaint();
							}
						}
					}
				}
		});
		
		canvas.addMouseMotionListener(new MouseAdapter() {			
			public void mouseDragged(MouseEvent e) {
				if(btn == MouseEvent.BUTTON1) {
					if(!stateManager.modeget()) {
						stateManager.mouseDrag(e.getX()-x,e.getY()-y);
						canvas.repaint();
					}else {
						if(med.getSelectedDrawings().size() != 0) {
							med.move(e.getX()-x,e.getY()-y);
							x = e.getX();
							y = e.getY();
							canvas.repaint();
						}else {
							med.dragRectangle(e.getX()-x,e.getY()-y);
							x = e.getX();
							y = e.getY();
							canvas.repaint();
						}
					}
				}
			}
		});
			
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					med.removeDrawings();
					canvas.repaint();
				}
				if(e.getKeyCode() == KeyEvent.VK_X) {
					med.cut();			      
					canvas.repaint();
				}
				if(e.getKeyCode() == KeyEvent.VK_C) {
					med.copy();
				}
				if(e.getKeyCode() == KeyEvent.VK_Z) {
					med.undo();
					canvas.repaint();
				}
				if(e.getKeyCode() == KeyEvent.VK_Y) {
					med.redo();
					canvas.repaint();
				}
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(300,400);
	}
	
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
	

}
