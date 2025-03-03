package Java_Second;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JFileChooser;

public class Mediator {
	Vector<Vector<MyDrawing>> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings;
	MyDrawing selectedDrawing = null;
    Vector<MyDrawing> buffer;
	MyDrawing rectangle = null;
	int nowN;
	int X,Y;
    private Stack<Vector<Vector<MyDrawing>>> undoStack;
    private Stack<Vector<Vector<MyDrawing>>> redoStack;
    
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<Vector<MyDrawing>>();
		selectedDrawings = new Vector<MyDrawing>();
        buffer = new Vector<MyDrawing>();
        nowN = 0;
        for(int i = 0; i < 10; i++) {
        	this.drawings.add(new Vector<MyDrawing>());
        }
        undoStack = new Stack<>();
        redoStack = new Stack<>();
	}
	
	public Enumeration<MyDrawing> drawingsElements(){
		return drawings.get(nowN).elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.get(nowN).add(d);
	}
	
	public void clearDrawing() {
		drawings.get(nowN).clear();
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.get(nowN).remove(d);
	}
	
	public void setSelectedDrawings(MyDrawing d) {
		this.selectedDrawings.add(d);
	}
	
	public Vector<MyDrawing> getSelectedDrawings(){
		return selectedDrawings;
	}
	
	public void changepage(int p) {
		nowN = p;
	}
	
	public void clearDelecctedDrawings() {
		selectedDrawings.clear();
		if(rectangle != null) {
			drawings.get(nowN).remove(rectangle);
			rectangle = null;
		}
	}
	
	public void addRectangle(int x,int y) {
		rectangle = new MyRectangle(x, y, 10, 10, Color.black, new Color(0,0,0,0), 1, false);
		System.out.print(drawings.get(nowN).size());
		addDrawing(rectangle);
		System.out.print(drawings.get(nowN).size());
		repaint();
	}
	
	public void dragRectangle(int x, int y) {
		rectangle.scale(x, y);
	}
	
	public MyDrawing getRectangle() {
		return rectangle;
	}
	
	public void move(int dx, int dy) {
		if(selectedDrawings.get(0) != null) {
			selectedDrawings.forEach(o -> o.move(dx,dy));
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public boolean setSelected(int x, int y) {
		if(rectangle != null && rectangle.contains(x, y)) {
			return false;
		}
		clearDelecctedDrawings();
		deselect();
	    for (int i = drawings.get(nowN).size() - 1; i >= 0; i--) {
	        MyDrawing d = drawings.get(nowN).get(i);
	        if (d.contains(x, y)) {
	            setSelectedDrawings(d);
	            d.setSelected(true);
	            return false;
	        }
	    }
	    return true;
	}
	
	public void setrectselect() {
	    for (int i = drawings.get(nowN).size() - 1; i >= 0; i--) {
	        MyDrawing d = drawings.get(nowN).get(i);
	        if (rectangle.getRegiion().contains(d.getRegiion())) {
	            setSelectedDrawings(d);
	            d.setSelected(true);
	        }
	    }
	    if(selectedDrawings.size() == 1) {
	    	clearDelecctedDrawings();
	    }
	}
	
	public void deselect() {
		for (int i = drawings.get(nowN).size() - 1; i >= 0; i--) {
	        MyDrawing d = drawings.get(nowN).get(i);
	        d.setSelected(false);
	    }
	}
	
	public void setColor(Color c) {
		if(this.selectedDrawings.size() != 0) {
			selectedDrawings.forEach(o -> o.setFillColor(c));
			if(rectangle != null) {
				rectangle.setFillColor(new Color(0,0,0,0));
			}
		}
		Stackpage();
	}
	
	public void setLineColor(Color c) {
		if(this.selectedDrawings.size() != 0) {
			selectedDrawings.forEach(o -> o.setLineColor(c));
			if(rectangle != null) {
				rectangle.setLineColor(Color.black);
			}
		}
		Stackpage();
	}
	
	public void setLineWidth(int l) {
		if(this.selectedDrawings.size() != 0) {
			selectedDrawings.forEach(o -> o.setLineWidth(l));
			if(rectangle != null) {
				rectangle.setLineWidth(1);
			}
		}
		Stackpage();
	}
	public void setShadow(boolean b) {
		if(this.selectedDrawings.size() != 0) {
			selectedDrawings.forEach(o -> o.setShadow(b));
			if(rectangle != null) {
				rectangle.setShadow(false);
			}
		}
		Stackpage();
	}
	
    public void clearBuffer() {
        buffer.clear();
    }
    

    public void cut() {
        clearBuffer();
        if(!selectedDrawings.isEmpty()) {
        	if(rectangle != null) {
	        	X = rectangle.getX();
	        	Y = rectangle.getY();
        	}else {
        		X = 0;
        		Y = 0;
        	}
            selectedDrawings.forEach(d -> buffer.add((MyDrawing) d.clone()));
            selectedDrawings.forEach(d -> removeDrawing(d));
            if(rectangle != null) {
            	buffer.remove(0);
            }
            repaint();
            Stackpage();
        }
    }

    public void copy() {
        clearBuffer();
        if(!selectedDrawings.isEmpty()) {
        	if(rectangle != null) {
	        	X = rectangle.getX();
	        	Y = rectangle.getY();
        	}else {
        		X = 0;
        		Y = 0;
        	}
            selectedDrawings.forEach(d -> buffer.add((MyDrawing) d.clone()));
            if(rectangle != null) {
            	buffer.remove(0);
            }
        }
    }

    public void paste(int x, int y){
        deselect();
        if(!buffer.isEmpty()) {
        	if(buffer.size() != 1) {
	            buffer.forEach(d -> {
	                MyDrawing clone = (MyDrawing) d.clone();
	                clone.setSelected(false);
	                clone.setLocation(clone.getX() - X + x, clone.getY() - Y + y);
	                addDrawing(clone);
	                clearDelecctedDrawings();
	            });
	            repaint();
        	}else {
        		buffer.forEach(d -> {
	                MyDrawing clone = (MyDrawing) d.clone();
	                clone.setSelected(false);
	                clone.setLocation(x, y);
	                addDrawing(clone);
	                clearDelecctedDrawings();
	            });
	            repaint();
        	}
        	Stackpage();
        }
    }
    
    public void removeDrawings() {
    	selectedDrawings.forEach(d -> removeDrawing(d));
    	repaint();
    	Stackpage();
    }
    
    public void saveDrawings() {
    	JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try{
            	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(drawings);
            } catch (Exception ex) {
            }
        }
    }

    public void loadDrawings() {
    	JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try{
            	ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                drawings = (Vector<Vector<MyDrawing>>) in.readObject();
                repaint();
            } catch (Exception ex) {
            }
        }
    }
    

    public void Stackpage() {
        undoStack.push(copyDrawings());
        redoStack.clear(); 
    }

    public Vector<Vector<MyDrawing>> copyDrawings() {
        Vector<Vector<MyDrawing>> copy = new Vector<>();
        for (Vector<MyDrawing> p : drawings) {
            Vector<MyDrawing> pageCopy = new Vector<>();
            for (MyDrawing d : p) {
                pageCopy.add((MyDrawing) d.clone());
            }
            copy.add(pageCopy);
        }
        return copy;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copyDrawings());
            drawings = undoStack.pop();
            repaint();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyDrawings());
            drawings = redoStack.pop();
            repaint();
        }
    }
    
}
