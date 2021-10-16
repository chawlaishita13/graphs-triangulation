

public class Shape implements ShapeInterface
{	
	LinkedL<Point> allPoints=new LinkedL<Point>();
	LinkedL<Triangle> allTriangles=new LinkedL<Triangle>();
	LinkedL<Edge> allEdges=new LinkedL<Edge>();
	public static int pc;
	
	public int TYPE_MESH(){
		int flag=2;
		//
		Node<Edge> edge=allEdges.getNodeAt(0);
		for(int i=0;i<allEdges.size();i++) {
			int size=edge.data.triangles.size();
			if(size>2)
				return 3;
			if(size==1) {
				flag=1;
			}
			edge=edge.next;
		}
		if(flag==2)
			return 1;
		
		return 2;
		}
	
    public int partition(Edge[] a,int left,int right) {
    	
    	Edge temp;
        int i=left+1;
        int pos=left;
        int j=right;
        Edge x=a[left];
        while(i<=j) {
           Edge e=a[pos];
           Edge u1=a[i]; 
           Edge u2=a[j];
           if(u1.edgesq()<x.edgesq()) {
        	   temp=a[i]; 
        	   a[i]=a[pos];
        	   a[pos]=temp;
        	   pos=i;
        	   i=i+1;

        }
           else  
        {
        	   temp=a[i];
        	   a[i]=a[j];
        	   a[j]=temp;
        	   j=j-1;

        }
           }
        a[j]=x;
    return pos;

    }

    public void quicksort(Edge[] a,int left,int right) {
    	if(left<right) {
    		int p=partition(a,left,right);
    		quicksort(a,left,p-1);
    		quicksort(a,p+1,right);
    		           }

    	}
	
    public EdgeInterface [] BOUNDARY_EDGES(){
		Edge[] arr=new Edge[allEdges.size()];
		int j=0;
		Node<Edge> edge=allEdges.getNodeAt(0);
		for(int i=0;i<allEdges.size();i++) {
			if(edge.data.triangles.size()==1) {
				arr[j]=edge.data;
				++j;
			}
			edge=edge.next;
		}
		Edge[] a=new Edge[j];
		for(int i=0;i<j;i++)
			a[i]=arr[i];
		if(j!=0)
			{quicksort(a,0,j-1);
//			for(int i=0;i<a.length;i++) {
//				System.out.println(a[i].a.getX()+" "+a[i].a.getY()+" "+a[i].a.getZ()+" and "+ a[i].b.getX()+" "+a[i].b.getY()+" "+a[i].b.getZ()+ " len "+a[i].edgesq());
//			}
			return a;
			}
		else
			return null;
		}
	
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		float x1=triangle_coord[0],y1=triangle_coord[1],z1=triangle_coord[2];
		float x2=triangle_coord[3],y2=triangle_coord[4],z2=triangle_coord[5];
		float x3=triangle_coord[6],y3=triangle_coord[7],z3=triangle_coord[8];
		Point A=new Point(x1,y1,z1);
		Point B=new Point(x2,y2,z2);
		Point C=new Point(x3,y3,z3);
		Triangle ABC=new Triangle(A,B,C);
		boolean flag=false;
		LinkedL<Triangle> list=new LinkedL<>();
		Node<Triangle> node=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++) {
			Triangle triangle=node.data;
			if(triangle.isSame(ABC)) {
				Node<Triangle> nodu=triangle.triangles.getNodeAt(0);
				for(int j=0;j<triangle.triangles.size();j++) {
					list.addLast(nodu.data);
					nodu=nodu.next;
				}
				flag=true;
			}
			node=node.next;	
		}
		if(flag==false)
			return null;

		Triangle[] arr=new Triangle[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
		
		}
		quicksortTri(arr,0,arr.length-1);
//		for(int i=0;i<arr.length;i++) {
//			System.out.println(arr[i].toString());
//		
//		}
		
		return arr;

		
		}

    public Triangle[] sort(Triangle[] arr) {
    	int size=arr.length;
    	for(int i=0;i<size;i++) {
    		for(int j=i+1;j<size;j++) {
    			Triangle x=arr[i];
    			Triangle y=arr[j];
    			if(x.x>y.x) {
    				arr[j]=x;
    				arr[i]=y;
    				}
    			}
    		}
    	return arr;
    	}
    
    public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
    	Point A= new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
    	LinkedL<Point> list=new LinkedL<Point>();
    	boolean flag=false;
    	Node<Point> node=allPoints.getNodeAt(0);
    	for(int i=0;i<allPoints.size();i++) {
    		Point point=node.data;
    		if(point.isSame(A)) {
    			for(int j=0;j<point.points.size();j++)
					list.addLast(point.points.getAt(j));
    				flag=true;
    				break;
    		}
    		node=node.next;
    		}
    	if(flag!=true)
    		return null;
    	Point[] arr=new Point[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
		//	System.out.print("["+arr[i].x+","+ arr[i].y+"," +arr[i].z+"]");
		}
    	return arr;
    	}
    public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
    	Point A= new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
    	LinkedL<Edge> list=new LinkedL<Edge>();
    	boolean flag=false;
    	Node<Point> node=allPoints.getNodeAt(0);
    	for(int i=0;i<allPoints.size();i++) {
    		Point point=node.data;
    		if(A.isSame(point)) {
    			flag=true;
    			for(int j=0;j<point.edges.size();j++){
    				list.addLast(point.edges.getAt(j));
    			}
    		}
    		node=node.next;
    	}
    	if(flag==false)
    		return null;
    	Edge[] arr=new Edge[list.size()];
    	for(int i=0;i<list.size();i++) {
    		arr[i]=list.getAt(i);
    	}
    	return arr;
    	}

    public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
    	float x1=triangle_coord[0],y1=triangle_coord[1],z1=triangle_coord[2];
		float x2=triangle_coord[3],y2=triangle_coord[4],z2=triangle_coord[5];
		float x3=triangle_coord[6],y3=triangle_coord[7],z3=triangle_coord[8];
		Point A=new Point(x1,y1,z1);
		Point B=new Point(x2,y2,z2);
		Point C=new Point(x3,y3,z3);
		Edge AB=new Edge(A,B);
		Edge BC=new Edge(B,C);
		Edge CA=new Edge(C,A);
		Edge[] arr=new Edge[3];
		Triangle ABC=new Triangle(A,B,C);
		boolean flag=false;
		Node<Triangle> node=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++) {
			Triangle triangle=node.data;
			if(triangle.isSame(ABC)) {
				flag=true;
				arr[0]=AB;
				arr[1]=BC;
				arr[2]=CA;
				break;
			}
			node=node.next;
		}
		if(flag==false)
			return null;
//		System.out.println(arr[0].toString());
//		System.out.println(arr[1].toString());
//		System.out.println(arr[2].toString());
		return arr;
		}

    public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
    	float x1=triangle_coord[0],y1=triangle_coord[1],z1=triangle_coord[2];
		float x2=triangle_coord[3],y2=triangle_coord[4],z2=triangle_coord[5];
		float x3=triangle_coord[6],y3=triangle_coord[7],z3=triangle_coord[8];
		Point A=new Point(x1,y1,z1);
		Point B=new Point(x2,y2,z2);
		Point C=new Point(x3,y3,z3);
		Point[] arr=new Point[3];
		Triangle ABC=new Triangle(A,B,C);
		boolean flag=false;
		Node<Triangle> node=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++) {
			Triangle triangle=node.data;
			if(triangle.isSame(ABC)) {
				flag=true;
				arr[0]=A;
				arr[1]=B;
				arr[2]=C;
				break;
			}
			node=node.next;
		}
		if(flag==false)
			return null;
//		System.out.println(arr[0].toString());
//		System.out.println(arr[1].toString());
//		System.out.println(arr[2].toString());
		return arr;
    	}
    
    public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
    	float x1=triangle_coord[0],y1=triangle_coord[1],z1=triangle_coord[2];
		float x2=triangle_coord[3],y2=triangle_coord[4],z2=triangle_coord[5];
		float x3=triangle_coord[6],y3=triangle_coord[7],z3=triangle_coord[8];
		Point A=new Point(x1,y1,z1);
		Point B=new Point(x2,y2,z2);
		Point C=new Point(x3,y3,z3);
		Triangle ABC=new Triangle(A,B,C);
		LinkedL<Triangle> list=new LinkedL<>();
		boolean flag=false;
		Node<Triangle> n=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++)
		{
			if(n.data.isSame(ABC)) {
				flag=true;
				break;
			}
			n=n.next;
		}
		if(flag==false)
			return null;
		Node<Point> node=allPoints.getNodeAt(0);
		for(int i=0;i<allPoints.size();i++) {
			Point point=node.data;
			if(point.isSame(A)) {
				for(int j=0;j<point.triangles.size();j++) {
					Triangle triangle=point.triangles.getAt(j);
					if(!triangle.isSame(ABC)) {
						list.addLast(triangle);
					}
				}
			}
			if(point.isSame(B)) {
				for(int j=0;j<point.triangles.size();j++) {
					Triangle triangle=point.triangles.getAt(j);
					if(!triangle.isSame(ABC)) {
						list.addLast(triangle);
					}
				}
			}
			if(point.isSame(C)) {
				for(int j=0;j<point.triangles.size();j++) {
					Triangle triangle=point.triangles.getAt(j);
					if(!triangle.isSame(ABC)) {
						list.addLast(triangle);
					}
				}
			}
			node=node.next;
		}
		Triangle[] arr=new Triangle[list.size()];
		Triangle[] a=new Triangle[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
		
		}
		quicksortTri(arr,0,arr.length-1);
		int i=0;
		int j=0;
		while(i<arr.length) {
			a[j]=arr[i];
			while(i+1<arr.length && arr[i].x==arr[i+1].x) {
					i++;
		}
			i++;
			j++;
		}
		Triangle[] b=new Triangle[j];
		for(int k=0;k<j;k++) {
			b[k]=a[k];
		//	System.out.println(b[k].toString());
		}
		
		return b;
		
    	
    }
    
    public int partition(Triangle[] a,int left,int right) {
    	
    	Triangle temp;
        int i=left+1;
        int pos=left;
        int j=right;
        Triangle x=a[left];
        while(i<=j) {
           Triangle e=a[pos];
           Triangle u1=a[i]; 
           Triangle u2=a[j];
           if(u1.x<x.x) {
        	   temp=a[i]; 
        	   a[i]=a[pos];
        	   a[pos]=temp;
        	   pos=i;
        	   i=i+1;

        }
           else  
        {
        	   temp=a[i];
        	   a[i]=a[j];
        	   a[j]=temp;
        	   j=j-1;

        }
           }
        a[j]=x;
    return pos;

    }

    public void quicksortTri(Triangle[] a,int left,int right) {
    	if(left<right) {
    		int p=partition(a,left,right);
    		quicksortTri(a,left,p-1);
    		quicksortTri(a,p+1,right);
    		           }

    	}

    public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
    	Point A= new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
    	LinkedL<Triangle> list=new LinkedL<Triangle>();
    	boolean flag=false;
    	Node<Point> node=allPoints.getNodeAt(0);
    	for(int i=0;i<allPoints.size();i++) {
    		Point point=node.data;
    		if(point.isSame(A)) {
    			flag=true;
    			for(int j=0;j<point.triangles.size();j++) {
    				Triangle triangle=point.triangles.getAt(j);
    				list.addLast(triangle);
    			}
    			break;
    		}
    		node=node.next;
    		}
    	if(flag==false)
    		return null;
    	Triangle[] arr=new Triangle[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
	//		System.out.print(arr[i].toString());
		}
    	return arr;
    	}
    
    public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
    	Point A= new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
    	LinkedL<Triangle> list=new LinkedL<Triangle>();
    	boolean flag=false;
    	Node<Point> node=allPoints.getNodeAt(0);
    	for(int i=0;i<allPoints.size();i++) {
    		Point point=node.data;
    		if(point.isSame(A)) {
    			flag=true;
    			for(int j=0;j<point.triangles.size();j++) {
    				Triangle triangle=point.triangles.getAt(j);
    				list.addLast(triangle);
    			}
    			break;
    		}
    		node=node.next;
    		}
    	if(flag==false)
    		return null;
    	Triangle[] arr=new Triangle[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
	//		System.out.print(arr[i].toString());
		}
    	return arr;
    	}

    public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
    	Point A=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
    	Point B=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		Edge AB=new Edge(A,B);
		boolean flag=false;
		LinkedL<Triangle> list=new LinkedL<>();
		Node<Edge> node=allEdges.getNodeAt(0);
		for(int i=0;i<allEdges.size();i++) {
			Edge edge=node.data;
			if(edge.isSame(AB)) {
				flag=true;
				for(int j=0;j<edge.triangles.size();j++) {
					Triangle triangle=edge.triangles.getAt(j);
					list.addLast(triangle);
				}
			}
			node=node.next;
		}
		if(flag==false)
			return null;
		Triangle[] arr=new Triangle[list.size()];
		for(int i=0;i<list.size();i++) {
			
			arr[i]=list.getAt(i);
		//	System.out.print(arr[i].toString());
		}
    	return arr;}
    
    public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
    	float x1=triangle_coord_1[0],y1=triangle_coord_1[1],z1=triangle_coord_1[2];
		float x2=triangle_coord_1[3],y2=triangle_coord_1[4],z2=triangle_coord_1[5];
		float x3=triangle_coord_1[6],y3=triangle_coord_1[7],z3=triangle_coord_1[8];
		float p1=triangle_coord_2[0],q1=triangle_coord_2[1],r1=triangle_coord_2[2];
		float p2=triangle_coord_2[3],q2=triangle_coord_2[4],r2=triangle_coord_2[5];
		float p3=triangle_coord_2[6],q3=triangle_coord_2[7],r3=triangle_coord_2[8];
		Point A=new Point(x1,y1,z1);
		Point B=new Point(x2,y2,z2);
		Point C=new Point(x3,y3,z3);
		Point P=new Point(p1,q1,r1);
		Point Q=new Point(p2,q2,r2);
		Point R=new Point(p3,q3,r3);
		Triangle ABC=new Triangle(A,B,C);
		Triangle PQR=new Triangle(P,Q,R);
		Node<Triangle> node=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++) {
			Triangle t=node.data;
			t.visited=false;
			if(t.isSame(ABC))
				ABC=t;
			if(t.isSame(PQR))
				PQR=t;
			node=node.next;
		}
    	return hasPath(ABC,PQR);
    	}
    

	private boolean hasPath(Triangle t1, Triangle t2) {
			boolean x=func(t1,t2);
			if(x)
				return true;
			if(t1.visited==true)
				return false;
			t1.visited=true;
			Node<Triangle> node=t1.triangles.getNodeAt(0);
			for(int i=0;i<t1.triangles.size();i++) {
				boolean b=hasPath(node.data,t2);
				if(b==true)
					return true;
				node=node.next;
			}
			t1.visited=false;
			return false;

	}
    
	private boolean func(Triangle t1, Triangle t2) {
		Node<Triangle> node=t1.triangles.getNodeAt(0);
		for(int i=0;i<t1.triangles.size();i++) {
			if(node.data.isSame(t2))
				return true;
			node=node.next;
		}
		return false;
	}
	
	private void func1(Triangle t) { 
        t.visited=true;
  //      t.comp=count;
        Node<Triangle> node=t.triangles.getNodeAt(0);
        for (int i=0;i<t.triangles.size();i++) { 
        	Triangle x=node.data;
    //    	x.comp=count;
            if(x.visited==false)
            	func1(x); 
            node=node.next;
        } 
  
    } 

    public int COUNT_CONNECTED_COMPONENTS(){
    	 int count=0;
    	 Node<Triangle> node=allTriangles.getNodeAt(0);
    	 for(int i=0;i<allTriangles.size();i++) {
 			Triangle t=node.data;
 			t.visited=false;
 			node=node.next;
    	 }
    	 Node<Triangle> nodu=allTriangles.getNodeAt(0);
         for(int i = 0; i < allTriangles.size(); i++) { 
         	
         	Triangle t=nodu.data;
         	
             if(t.visited==false) {  
                 func1(t); 
                 count++;
             } 
             nodu=nodu.next;
         } 
 //        System.out.println(count);
         return count;}

    
	private void func2(Point p,float[] count) { 
		  visited[p.count]=true;
		  Node<Point> node=p.points.getNodeAt(0);
	        for (int i=0;i<p.points.size();i++) { 
	        	Point x=node.data;
	            if(visited[x.count]==false) {
	            	count[0]+=1;
	            	count[1]+=x.x;
	            	count[2]+=x.y;
	            	count[3]+=x.z;
	            	visited[x.count]=true;
	            	func2(x,count); 
	            }
	            node=node.next;
	        } 
    }
	boolean[] visited;
    public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
    	Point A=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
    	float[] count=new float[4];
    	count[0]=1;
    	count[1]=A.x;
    	count[2]=A.y;
    	count[3]=A.z;
    	
    	Point point=new Point(0,0,0);
    	visited=new boolean[allPoints.size()];
    	Node<Point> node=allPoints.getNodeAt(0);
           for(int i = 0; i < allPoints.size(); i++) { 
           	Point p=node.data;
               if(!visited[p.count] && p.isSame(A)) {
                   func2(p,count); 
                   break;
               }
               node=node.next;
           }
        point.x=count[1]/count[0];
        point.y=count[2]/count[0];
        point.z=count[3]/count[0];
 //     System.out.println(point.x+" "+point.y+" "+point.z);
    	return point;
    	}
    
    
    public PointInterface [] CENTROID (){
    	float[] count=new float[4];
    	int a=COUNT_CONNECTED_COMPONENTS();
    	Point[] arr=new Point[a];
    	int j=0;
      visited=new boolean[allPoints.size()];
      Node<Point> node=allPoints.getNodeAt(0);
           for(int i = 0; i < allPoints.size(); i++) { 
           	Point p=node.data;
               if(visited[p.count]==false) { 
            	   Point point=new Point(0,0,0);
            	   count[0]=1;
               		count[1]=p.x;
               		count[2]=p.y;
               		count[3]=p.z;
                   func2(p,count); 
                   point.x=count[1]/count[0];
                   point.y=count[2]/count[0];
                   point.z=count[3]/count[0];
                   arr[j]=point;
                   j++;
               }
               node=node.next;
           }
//       for(int i=0;i<arr.length;i++) {
//    	   System.out.println(arr[i].toString());
//       }
       
    	return arr;
}
    private class Pair{
		Triangle tria;
		int dist;
	}
    
	private int bft(Triangle t) {
		Node<Triangle> node=allTriangles.getNodeAt(0);
		for(int i=0;i<allTriangles.size();i++) {
			node.data.vis=false;
			node=node.next;
		}
		LinkedL<Pair> queue = new LinkedL<>();
		Pair sp = new Pair();
		sp.tria=t;
		sp.dist=0;
		int max=0;
			queue.addLast(sp);
			sp.tria.vis=true;

			// work till queue is not empty
			while (!queue.isEmpty()) {
				Pair rp = queue.removeFirst();
				// remove the pair from queue
				Node<Triangle> nodu=rp.tria.triangles.getNodeAt(0);
				for (int j=0;j<rp.tria.triangles.size();j++) {
					Triangle yay=nodu.data;
					// work only for unprocessed nbrs
					if (yay.vis==false) {
						yay.vis=true;
						Pair np = new Pair();
						np.tria = yay;
						np.dist = rp.dist + 1;
						if(max<np.dist)
							max=np.dist;
						queue.addLast(np);
						
					}
					nodu=nodu.next;
					}

			}
			return max;
		}
	int maxbft;
	int counter;
	private void func3(Triangle t) { 
        t.visited=true;
        counter++;
        Node<Triangle> node=t.triangles.getNodeAt(0);
        for (int i=0;i<t.triangles.size();i++) { 
        	Triangle x=node.data;
            if(x.visited==false) {
            	int m=bft(x);
            	if(m>maxbft)
            		maxbft=m;
            	func3(x); 
            }
            node=node.next;
        } 
  
    }
	
	public int MAXIMUM_DIAMETER(){
		int[] max=new int[allTriangles.size()];
		int[] cou=new int[allTriangles.size()];
		
		Node<Triangle> node=allTriangles.getNodeAt(0);
		 	 for(int i=0;i<allTriangles.size();i++) {
 			Triangle t=node.data;
 			t.visited=false;
 			node=node.next;
    	 }
		 	 int j=0;
		 	Node<Triangle> nn=allTriangles.getNodeAt(0);
         for(int i = 0; i < allTriangles.size(); i++) { 
         	
         	Triangle t=nn.data;
             if(t.visited==false) { 
            	 counter=0;
            	 maxbft=0;
                 func3(t); 
                 cou[j]=counter;
                 max[j]=maxbft;
                 
             } 
             j++;
             nn=nn.next;
         } 
         int m=0;
         int pos=-1;
         for(int i=0;i<cou.length;i++) {
        	 if(m<cou[i]) {
        		 m=cou[i];
        		 pos=i;
        	 }
         }
     
       System.out.println(max[pos]);
         return max[pos];
         
//		int noc=COUNT_CONNECTED_COMPONENTS();
//		int max_comp=0;
//		int max_tr=0;
//		for(int i=0;i<noc;i++) {
//			if(max_tr<cou[i]) {max_comp=i+1;max_tr=cou[i];}
//		}
//		LinkedL<Triangle>t=new LinkedL<>();
//		Node<Triangle> node=allTriangles.getNodeAt(0);
//		for(int i=0;i<allTriangles.size();i++ ) {
//			Triangle tri=node.data;
//			if(tri.comp==max_comp) {
//				t.addLast(tri);
//			}
//			node=node.next;
//		}
//		int[][] d=new int[t.size()][t.size()];
//		for(int i=0;i<t.size();i++) {
//			for(int j=0;j<t.size();j++) {
//				if(i!=j) {
//					
//				}
//				else d[i][j]=0;
//			}
//		}
			}
   
	public PointInterface [] CLOSEST_COMPONENTS(){
		 int yay=0;
		 visited=new boolean[allPoints.size()];
    	 Node<Point> p1=allPoints.getNodeAt(0);
    	 
         for(int i = 0; i < allPoints.size(); i++) { 
             if(visited[p1.data.count]==false) {  
                 func4(p1.data,yay); 
                 
                 yay++;
             } 
             p1=p1.next;
         }
//         for(int i=0;i<allPoints.size();i++) {
//       // 	 System.out.println(allPoints.getAt(i).toString());
//        	allPoints.getAt(i).points.display();
////        	System.out.println();
////        	System.out.println(allPoints.getAt(i).count);
//         }
         float min=Integer.MAX_VALUE;
         Point s=null,t=null;
         Node<Point> a=allPoints.getNodeAt(0);
         for(int i=0;i<allPoints.size();i++) {
        	 Node<Point> b=allPoints.getNodeAt(0);
        	 for(int j=0;j<allPoints.size();j++) {
        		 float x=a.data.getX()-b.data.getX();
        			float y=a.data.getY()-b.data.getY();
        			float z=a.data.getZ()-b.data.getZ();
        			float len= x*x+y*y+z*z;
        		 if(len<min && a.data.counter!=b.data.counter)
        		 { 
        			 //System.out.println(a.data.counter+" "+b.data.counter);
        			 s=a.data;
        			 t=b.data;
        			 min=len;
        		 }
        		 b=b.next;
        	 }
        	 a=a.next;
         }
   //      System.out.println(s+" "+t);
       Point[] fin=new Point[] {s,t};
         return fin;
	
	}
	
	private void func4(Point p,int yay) { 
        visited[p.count]=true;
        p.counter=yay;
        Node<Point> x=p.points.getNodeAt(0);
        for (int i=0;i<p.points.size();i++) { 
            if(visited[x.data.count]==false)
            {	
            	x.data.counter=yay;
            	func4(x.data,yay); 
            	
            }
            x=x.next;
        } 
  
    } 
	
public boolean ADD_TRIANGLE(float [] triangle_coord){
	float x1=triangle_coord[0];
	float y1=triangle_coord[1];
	float z1=triangle_coord[2];
	float x2=triangle_coord[3];
	float y2=triangle_coord[4];
	float z2=triangle_coord[5];
	float x3=triangle_coord[6];
	float y3=triangle_coord[7];
	float z3=triangle_coord[8];
    float k1=(x2-x1)*(y2-y3);
    float k2=(y2-y1)*(x2-x3);
    float k3=(z2-z1)*(x2-x3);
    float k4=(x2-x1)*(z2-z3);
    float k5=(y2-y1)*(z2-z3);
    float k6=(z2-z1)*(y2-y3);

    if(Math.abs(k1-k2)<0.001 && Math.abs(k3-k4)<0.001 && Math.abs(k5-k6)<0.001)
    	return false;
    else {		
    	Point A=new Point(x1,y1,z1);
    	Point B=new Point(x2,y2,z2);
    	Point C=new Point(x3,y3,z3);
    	Edge AB=new Edge(A,B);
    	Edge BC=new Edge(B,C);
    	Edge CA=new Edge(C,A);
    	Triangle ABC= new Triangle(A,B,C);
//
//                       t.pos=mycount;
//
//                       mycount++;
        Point x=null,y=null,z=null;
        boolean s1=false,s2=false,s3=false;
    	boolean f1=false,f2=false;
    	boolean f3=false,f4=false,f5=false,f6=false;
    	int i1=-1,i2=-1,i3=-1;
    	boolean p=false,q=false,r=false;
    	Node<Edge> node=allEdges.getNodeAt(0);
    	for(int k=0;k<allEdges.size();k++) {
    		if(node.data.isSame(AB) ) {
    			node.data.triangles.addLast(ABC);
    			ABC.edges.addLast(node.data);
    			p=true;
    		}
    		if(node.data.isSame(BC)) {
    			node.data.triangles.addLast(ABC);
    			ABC.edges.addLast(node.data);
    			q=true;

    		}
    			
    		if(node.data.isSame(CA) ) {
    			node.data.triangles.addLast(ABC);
    			ABC.edges.addLast(node.data);
    			r=true;
    		}
    		node=node.next;
    	}
    	if(p==false) {
    		allEdges.addLast(AB);
    		ABC.edges.addLast(AB);                     
                }
    	if(q==false ) {
    		allEdges.addLast(BC);
    		ABC.edges.addLast(BC);
    			                }
    	if(r==false) {
    		allEdges.addLast(CA);
    		ABC.edges.addLast(CA);
                              }
    	Node<Point> poi=allPoints.getNodeAt(0);
    	for(int yay=0;yay<allPoints.size();yay++)
    	{
    		if(A.isSame(poi.data)) {
    			i1=poi.data.count;
    			x=poi.data;
    			s1=true;

    		}

    		if(B.isSame(poi.data)) {
    			i2=poi.data.count;
    			y=poi.data;
    			
    			s2=true;
}
    		if(C.isSame(poi.data)) {
    			i3=poi.data.count;
    			z=poi.data;
    			s3=true;

                                  }
    		poi=poi.next;

                       }
    	poi=allPoints.getNodeAt(0);
    	boolean found1=false,found2=false,found3=false;
    	for(int oye=0;oye<allPoints.size();oye++) {
    		if(A.isSame(poi.data)) {   ////////point a is already existing
    			poi.data.triangles.addLast(ABC);
    			found1=true;
    			Node<Edge> e= poi.data.edges.getNodeAt(0);
    			for(int j=0;j<poi.data.edges.size();j++) {
    				if(e.data.isSame(AB) )
    				{
    					f1=true;
    				}
    				if(e.data.isSame(CA)) {
    					f2=true;
    				}
    				e=e.next;
    			}
    			if(f1==false)
    			{
    				if(s2==true)         
    				{
    					B.count=i2;
    					B=y;
    					
    					
    				}
    				poi.data.points.addLast(B);  
    				poi.data.edges.addLast(AB);
    			}
    			if(f2==false) {
    				if(s3==true)
    				{C.count=i3;
    				C=z;
    				}
    				poi.data.points.addLast(C);                                       
    				poi.data.edges.addLast(CA);
       }
    		}
    		if(B.isSame(poi.data)) {   
    			poi.data.triangles.addLast(ABC);
    			found2=true;
    			Node<Edge> e= poi.data.edges.getNodeAt(0);
    			for(int j=0;j<poi.data.edges.size();j++) {
    					
    				if(e.data.isSame(AB) )
    				{
    					f3=true;
    				}
    				if(e.data.isSame(BC)) {
    					f4=true;
    				}
    				
    				e=e.next;
    			}
    			if(f3==false )
    			{
    				if(s1==true)
    				{   A.count=i1;
    					A=x;
    				}
    				poi.data.points.addLast(A);
    				poi.data.edges.addLast(AB);
    			}
    			if(f4==false) {
    				if(s3==true) {
    					C.count=i3;
    					C=z;
    				}
    				poi.data.points.addLast(C);
    				poi.data.edges.addLast(BC);
    			}
    		}
    		if(C.isSame(poi.data)) {   
    			poi.data.triangles.addLast(ABC);
    			found3=true;
    			Node<Edge> e= poi.data.edges.getNodeAt(0);
    			for(int j=0;j<poi.data.edges.size();j++) {
                                                                 
    				if(e.data.isSame(BC))
    				{
    					f5=true;
    				}
    				if(e.data.isSame(CA)) {
    					f6=true;
    				}
    				e=e.next;
    			}
    			if(f5==false)
    			{
    				if(s2==true)
    				{         
    					B.count=i2;
    					B=y;
    				}
    				poi.data.points.addLast(B);
                                                                 
    				poi.data.edges.addLast(BC);
    			}

    			if(f6==false  ) {
    				if(s1==true)
    				{
    					A.count=i1;
    					A=x;
    				}
    				poi.data.points.addLast(A);
                                                                  
    				poi.data.edges.addLast(CA);
    			}
    		}
    		poi=poi.next;
                       }
    	if(found1==false) {                                
    		A.count=pc;
    		pc++;
    		allPoints.addLast(A);
    		if(s2==true)
    			B=y;
    		A.points.addLast(B);
    		if(s3==true)
    			C=z;
    		A.points.addLast(C);
    		A.edges.addLast(AB);
    		A.edges.addLast(CA);
    		A.triangles.addLast(ABC);
    	}
    	if(found2==false) {
    		B.count=pc;
    		pc++;
    		allPoints.addLast(B);
    		if(s1==true)
    			A=x;
    		B.points.addLast(A);
    		if(s3==true)
    			C=z;
    		B.points.addLast(C);
    		B.edges.addLast(AB);
    		B.edges.addLast(BC);
    		B.triangles.addLast(ABC);
            }
    	if(found3==false) {
    		C.count=pc;
    		pc++;
    		if(s1==true)
    			A=x;
    		C.points.addLast(A);
    		if(s2==true)
    			B=y;
    		C.points.addLast(B);
    		allPoints.addLast(C);
    		C.edges.addLast(CA);
    		C.edges.addLast(BC);
    		C.triangles.addLast(ABC);
            }	
    	BC.triangles.addLast(ABC);
    	CA.triangles.addLast(ABC);
    	AB.triangles.addLast(ABC);
    	Node<Triangle> tria=allTriangles.getNodeAt(0);
    	for(int k=0;k<allTriangles.size();k++)
    	{
    		Node<Edge> e=tria.data.edges.getNodeAt(0);
    		for(int j=0;j<tria.data.edges.size();j++ ) {             
    			if(AB.isSame(e.data) || BC.isSame(e.data)|| CA.isSame(e.data)) {
    				tria.data.triangles.addLast(ABC);
    				ABC.triangles.addLast(tria.data);
    			}
    			e=e.next;
    		}
    		tria=tria.next;
    	}
    	allTriangles.addLast(ABC);
    	return true;

            }

           

            }
}

