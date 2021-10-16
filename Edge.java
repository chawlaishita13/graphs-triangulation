
public class Edge implements EdgeInterface {
	Point a;
	Point b;
	LinkedL<Triangle> triangles;
	
	public Edge(Point x, Point y) {
		a=x;
		b=y;
		triangles=new LinkedL<Triangle>();
	}
	
	public PointInterface[] edgeEndPoints() {
		// TODO Auto-generated method stub
		Point[] arr=new Point[2];
		arr[0]=a;
		arr[1]=b;
		return arr;
	}
	public float edgesq() {
		float x=a.getX()-b.getX();
		float y=a.getY()-b.getY();
		float z=a.getZ()-b.getZ();
		return x*x+y*y+z*z;
		
	}
	
	public boolean isSame(Edge edge) {
		if(a.isSame(edge.a) && b.isSame(edge.b) )
			return true;
		if(a.isSame(edge.b) && b.isSame(edge.a))
			return true;
		return false;
	}
	public String toString() {
		return (a.x+","+a.y+","+a.z+" and "+b.x+","+b.y+","+b.z);
	}
	
}
