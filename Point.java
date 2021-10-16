
public class Point implements PointInterface{
	float x,y,z;
	LinkedL<Edge> edges;
	LinkedL<Triangle> triangles;
	boolean visited =false;
	LinkedL<Point> points;
	int counter;
	int count;
	public Point(float x,float y, float z) {
		this.x=x;
		this.y=y;
		this.z=z;
		triangles=new LinkedL<Triangle>();
		edges=new LinkedL<Edge>();
		points=new LinkedL<Point>();
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public float[] getXYZcoordinate() {
		// TODO Auto-generated method stub
		float[] a=new float[3];
		a[0]=x;
		a[1]=y;
		a[2]=z;
		return a;
	}
	
	public boolean isSame(PointInterface point) {
		if(x==point.getX() && y==point.getY()&& z==point.getZ())
			return true;
		else
			return false;
	}
	
	public String toString() {
		return x+","+y+","+z;
	}

	
}
