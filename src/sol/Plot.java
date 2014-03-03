package sol;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

import com.sun.org.apache.xpath.internal.axes.AxesWalker;

public class Plot {

	public static void main(String[] args) {
		//new Plot().main();
		// define your data
		/*double[] x = { 0, 1, 3, 5, 5, 8, 7, 9, 2, 5, 7, 8, 9, 2, 1 };
		int noOfBins = 10;
		for (int i = 0; i < 3; i++){
			try {
				histogram("Log Normal population", x, noOfBins, Constants.V4_RES_DATA+"figs/tmp"+i+".png");
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}*/
		
	scatter3D("3D", new double[]{1,1,1,2,2,2,3,3,3}, new double[]{20,40,60,20,40,60,20,40,60}, new double[]{0.1,0.2,0.3,
		0.4,0.5,0.6,0.7,0.8,0.9},3, new String[]{"0-20","20-40","40-60"});
	}

	public static void scatter3D(String title, double[] x, double[] y, double [] z, int noOfBinsOnX, String[] spdRange){
		// create your PlotPanel (you can use it as a JPanel) with a legend at SOUTH
		Plot3DPanel plot = new Plot3DPanel("SOUTH");
 
		// add scatter plot plot to the PlotPanel
		int sIdx=0, bin=0;
		while(sIdx<z.length){
			plot.addLinePlot("Spd: "+spdRange[bin],subarray(x, sIdx, noOfBinsOnX), subarray(y, sIdx, noOfBinsOnX),subarray(z, sIdx, noOfBinsOnX));
			sIdx+=noOfBinsOnX;
		}
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
	
	public static double[] subarray(double[] arr, int sIdx, int length){
		double[] ret=new double[length];
		for(int i=sIdx;i<sIdx+length;i++){
			ret[i-sIdx]=arr[i];
		}
		return ret;
	}
	
	
	public static void histogram(String title, double[] values, int noOfBins, String saveFilePath) {
		try {
			// create your PlotPanel (you can use it as a JPanel)
			Plot2DPanel plot = new Plot2DPanel();
			// add the histogram of x to the PlotPanel
			plot.addHistogramPlot(title, values, noOfBins);
			
			Max max=new Max();
			plot.setFixedBounds(0, 0, max.evaluate(values)+1);
			
			// put the PlotPanel in a JFrame like a JPanel
			JFrame frame = new JFrame(title);
			frame.setSize(600, 600);
			//frame.setLayout(new FlowLayout());
			//frame.add(plot);
			frame.setContentPane(plot);
			frame.setVisible(true);
			
			File savedFile=new File(saveFilePath);
			//System.out.println(savedFile.getCanonicalPath());
			//plot.toGraphicFile(savedFile);//need a frame to run
						
			//frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
}
