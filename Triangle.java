

public class Triangle implements TriangleInterface{
	Point a;
	Point b;
	Point c;
	LinkedL<Point> points;
	LinkedL<Edge> edges;
	LinkedL<Triangle> triangles;
//	public int comp;
	static int count=0;
	int x;
	boolean visited=false;
	boolean vis=false;
	public Triangle(Point a,Point b,Point c) {
		this.a=a;
		this.b=b;
		this.c=c;
		points=new LinkedL<Point>();
		edges=new LinkedL<Edge>();
		triangles=new LinkedL<Triangle>();
		count++;
		x=count;
//		this.comp=0;
	}
	public PointInterface[] triangle_coord() {
		// TODO Auto-generated method stub
		Point[] arr=new Point[3];
		arr[0]=a;
		arr[1]=b;
		arr[2]=c;
		return arr;
	}
	public String toString() {

        return "("+a.getX()+" ,"+a.getY()+" ,"+a.getZ()+" ) .( "+b.getX()+" ,"+b.getY()+" ,"+b.getZ()+") .("+c.getX()+" ,"+c.getY()+" ,"+c.getZ()+")";

}	
	public boolean isSame(Triangle T) {
			Point P11 = T.a;
			Point P21 = T.b;
			Point P31 = T.c;
			if((P11.isSame(a) && P21.isSame(b) && P31.isSame(c))||(P11.isSame(a) && P21.isSame(c) && P31.isSame(b)) || (P11.isSame(b) && P21.isSame(a) && P31.isSame(c)) || (P11.isSame(b) && P21.isSame(c) && P31.isSame(a)) ||
			(P11.isSame(c) && P21.isSame(a) && P31.isSame(b)) || (P11.isSame(c) && P21.isSame(b) && P31.isSame(a)))
			return true;
			return false;
		}
	

    
	
	
}
