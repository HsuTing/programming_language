

public class Gaussian_Template {
	
	private int Radius;
	private double Variance;
	private double[][]Template;
	
	public Gaussian_Template(){
		this.Radius = 1;
		this.Variance = 1.0;
		this.setTemplate(this.Radius, this.Variance);
	}
	
	public Gaussian_Template(int radius, double variance){
		this.Radius = radius;
		this.Variance = variance;
		this.setTemplate(this.Radius, this.Variance);
	} 
	
	private void setTemplate(int radius, double variance){
		double sum = 0;
		this.Template = new double[radius * 2 + 1][radius * 2 + 1];
		
		for(int i = -radius; i <= radius; i++)
			for(int j = -radius; j <= radius; j++){
				this.Template[i + radius][j + radius] = (1 / (2 * Math.PI * variance * variance)) * Math.exp(-1 * (i * i + j * j) / (2 * variance * variance));
				sum = sum + this.Template[i + radius][j + radius];
			}
		
		for(int i = -radius; i <= radius; i++)
			for(int j = -radius; j <= radius; j++)
				this.Template[i + radius][j + radius] = this.Template[i + radius][j + radius] / sum;		
	}
	
	public double getTemplate(int x, int y){
		return this.Template[y][x];
	}
	
	public int getRadius(){
		return this.Radius;
	}
}
