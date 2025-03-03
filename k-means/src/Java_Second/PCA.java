package Java_Second;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.apache.commons.math4.legacy.linear.Array2DRowRealMatrix;
import org.apache.commons.math4.legacy.linear.EigenDecomposition;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.RealVector;
import org.apache.commons.math4.legacy.stat.correlation.Covariance;
import org.apache.commons.math4.legacy.stat.descriptive.moment.Mean;
import org.apache.commons.math4.legacy.stat.descriptive.moment.StandardDeviation;

public class PCA extends JPanel {
    private double[][] transformedData;
    private Mediator med;
    private double[][] data;
    List<String[]> outputdata;

    public PCA(Mediator med) {
    	this.med = med;
    }
    public void uploaddata() {
    	JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        List<double[]> dataList = new ArrayList<>();
        outputdata = new ArrayList<>();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    double[] row = new double[values.length - 1];
                    for (int i = 1; i < values.length; i++) {
                        row[i - 1] = Double.parseDouble(values[i]);
                    }
                    outputdata.add(values);
                    dataList.add(row);
                }
                br.close();	                
            } catch (Exception ex) {
            }
        }
        data = new double[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
    }
    public void pca() {
        
        Mean mean = new Mean();
        StandardDeviation stdDev = new StandardDeviation();
        double[] means = new double[data[0].length];
        double[] stdDevs = new double[data[0].length];
        transformedData = new double[data.length][data[0].length+4];
        
        for (int i = 0; i < data[0].length; i++) {
            double[] column = new double[data.length];
            for (int j = 0; j < data.length; j++) {
                column[j] = data[j][i];
            }
            means[i] = mean.evaluate(column);
            stdDevs[i] = stdDev.evaluate(column);
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = (data[i][j] - means[j]) / stdDevs[j];
            }
        }
        

        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(data, false);
        Covariance covariance = new Covariance(matrix);
        RealMatrix covarianceMatrix = covariance.getCovarianceMatrix();
        

        EigenDecomposition ed = new EigenDecomposition(covarianceMatrix);
        
        double[] eigenValues = ed.getRealEigenvalues();
        

        Integer[] indices = new Integer[eigenValues.length];
        for (int i = 0; i < eigenValues.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (i1, i2) -> Double.compare(eigenValues[i2], eigenValues[i1]));


        RealVector pc1 = ed.getEigenvector(indices[0]);
        RealVector pc2 = ed.getEigenvector(indices[1]);

        double[] pc1Data = matrix.operate(pc1).toArray();
        double[] pc2Data = matrix.operate(pc2).toArray();

        
        double pc1Min = Double.MAX_VALUE;
        double pc1Max = Double.MIN_VALUE;
        for (double v : pc1Data) {
            if (v < pc1Min) pc1Min = v;
            if (v > pc1Max) pc1Max = v;
        }

        double pc2Min = Double.MAX_VALUE;
        double pc2Max = Double.MIN_VALUE;
        for (double v : pc2Data) {
            if (v < pc2Min) pc2Min = v;
            if (v > pc2Max) pc2Max = v;
        }

        for (int i = 0; i < data.length; i++) {
            transformedData[i][0] = 500 * (pc1Data[i] - pc1Min) / (pc1Max - pc1Min) + 200;
            transformedData[i][1] = 500 * (pc2Data[i] - pc2Min) / (pc2Max - pc2Min) + 200;
        }

        med.clearDrawing();
        
        for (int i = 0; i < transformedData.length; i++) {
        	MyDrawing d = new MyOval((int)transformedData[i][0], (int)transformedData[i][1], 0,0, Color.black, Color.white, 1,false);
    		d.setSize(10, 10);
    		med.addDrawing(d);
	    }
    }
    
    public double distance(double[] p1, double[] p2) {
    	return Math.sqrt(Math.pow(p1[0]-p2[0],2) + Math.pow(p1[0]-p2[1], 2));
    }
    
    public void kmeans(int c){
    		Random random = new Random();
    		double[][] cluster1 = new double[c][2];
	    	double[][] cluster2 = new double[c][2];
	    	switch(c) {
	    	case 2:
		    	cluster1[0][0] = random.nextInt(250) + 200;
		    	cluster1[0][1] = random.nextInt(500) + 200;
		    	cluster1[1][0] = random.nextInt(250) + 450;
		    	cluster1[1][1] = random.nextInt(500) + 200;
		    	break;
	    	case 3:
		    	cluster1[0][0] = random.nextInt(166) + 200;
		    	cluster1[0][1] = random.nextInt(500) + 200;
		    	cluster1[1][0] = random.nextInt(166) + 366;
		    	cluster1[1][1] = random.nextInt(500) + 200;
		    	cluster1[2][0] = random.nextInt(166) + 532;
		    	cluster1[2][1] = random.nextInt(500) + 200;
		    	break;
	    	case 4:
	    		cluster1[0][0] = random.nextInt(250) + 200;
		    	cluster1[0][1] = random.nextInt(250) + 200;
		    	cluster1[1][0] = random.nextInt(250) + 450;
		    	cluster1[1][1] = random.nextInt(250) + 200;
		    	cluster1[2][0] = random.nextInt(250) + 200;
		    	cluster1[2][1] = random.nextInt(250) + 450;
		    	cluster1[3][0] = random.nextInt(250) + 450;
		    	cluster1[3][1] = random.nextInt(250) + 450;
	    	default:
	    		break;
	    	}

	    for (int n = 0; n<40; n++) {
	    	for (int i = 0; i < transformedData.length; i++) {
	    		double minD = Double.MAX_VALUE;
	    		for ( int j = 0; j < c; j++) {
	    			double distance = distance(transformedData[i],cluster1[j]);
	    			if(distance < minD) {
	    				for (int m = 2; m < transformedData[i].length; m++) {
	    					transformedData[i][m]=0;
	    				}
	    				transformedData[i][j+2] = 1;
	    				minD = distance;
	    			}
	    		}
	    	}
	    	for (int i = 0; i < c; i++) {
	    		cluster2[i][0] = 0;
	    		cluster2[i][1] = 0;
	    		int size = 0;
	    		for (int j = 0; j < transformedData.length; j++) {
	    			cluster2[i][0] +=  transformedData[j][0]*transformedData[j][i+2];
	    			cluster2[i][1] +=  transformedData[j][1]*transformedData[j][i+2];
	    		}
	    		for (int j = 0; j < transformedData.length; j++) {
	    			size +=  transformedData[j][i+2];
	    		}
	    		if(size == 0) {
	    			continue;
	    		}
	    		cluster1[i][0] = cluster2[i][0]/size;
	    		cluster1[i][1] = cluster2[i][1]/size;
	    	}
    	}
	    med.clearDrawing();
	    
	    for (int i = 0; i < transformedData.length; i++) {
	    	if(transformedData[i][2] == 1) {
	    		MyDrawing d = new MyOval((int)transformedData[i][0], (int)transformedData[i][1], 0,0, Color.black, Color.red, 1,false);
	    		d.setSize(10, 10);
	    		med.addDrawing(d);
	    	}
	    	if(transformedData[i][3] == 1) {
	    		MyDrawing d = new MyOval((int)transformedData[i][0], (int)transformedData[i][1], 0,0, Color.black, Color.blue, 1,false);
	    		d.setSize(10, 10);
	    		med.addDrawing(d);
	    	}
	    	if(transformedData[i][4] == 1) {
	    		MyDrawing d = new MyOval((int)transformedData[i][0], (int)transformedData[i][1], 0,0, Color.black, Color.yellow, 1,false);
	    		d.setSize(10, 10);
	    		med.addDrawing(d);
	    	}
	    	if(transformedData[i][5] == 1) {
	    		MyDrawing d = new MyOval((int)transformedData[i][0], (int)transformedData[i][1], 0,0, Color.black, Color.green, 1,false);
	    		d.setSize(10, 10);
	    		med.addDrawing(d);
	    	}
	    }
    	
    }
    
    public void downloaddata(){
    	List<String[]> result = new ArrayList<>();
    	for (int i = 0; i < transformedData.length; i++) {
    		String[] row = new String[4];
    		for (int j = 2; j < 6; j++) {
    			row[j-2] = Double.toString(transformedData[i][j]);
    		}
    		result.add(row);
    	}
    	List<String[]> resultdata = new ArrayList<>();
    	for (int i = 0; i < outputdata.size(); i++) {
    		resultdata.add(new String[outputdata.get(i).length]);
    	}
    	for (int i = 0; i < outputdata.size(); i++) {
    		String[] combinedRow = new String[outputdata.get(i).length + result.get(i).length];
            System.arraycopy(outputdata.get(i), 0, combinedRow, 0, outputdata.get(i).length);
            System.arraycopy(result.get(i), 0, combinedRow, outputdata.get(i).length, result.get(i).length);
            resultdata.set(i, combinedRow);
        }
    	System.out.println("Output Data:");
        for (String[] row : resultdata) {
            System.out.println(String.join(", ", row));
        }
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String file = fc.getSelectedFile().getAbsolutePath();
	    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	            for (String[] row1 : resultdata) {
	                StringBuilder sb = new StringBuilder();
	                for (int i = 0; i < row1.length; i++) {
	                    if (i > 0) {
	                        sb.append(",");
	                    }
	                    sb.append(row1[i]); 
	                }
	                writer.write(sb.toString()); 
	                writer.newLine(); 
	            }
	        } catch (IOException e) {
	        }
        }
    }

   
}
