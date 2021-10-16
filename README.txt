README

Shape class

ADD_TRIANGLE()-
This query creates a creates- a triangle from 3 points 
Checks if the traingle can be created or not. If possible then it creates a new triangle accoording to the given coordinates and adds it to the list allTriangles.
      
TYPE_MESH()-
This query returns the type of mesh created by the triangles.
Returns 1 if all edges are a part of exactly two triangles.
Returns 3 if at least one edge is a part of more than two triangles.
Returns 2 if at least one edge is a part of only one triangles and other edges are a part of either one or two triangles.
          
BOUNDARY_EDGES()-
Returns an array of edges that are a part of exactly one triangle and sorts them
          
COUNT_CONNECTED_COMPONENTS()-
Uses bft to find the number of connected components.

NEIGHBORS_OF_TRIANGLE()-
Returns an array of the neighbors of the given triangle.      
 
EDGE_NEIGHBOR_TRIANGLE()-
Returns an array of the edges of the triangle.
 
VERTEX_NEIGHBOR_TRIANGLE()-
Returns an array of the vertices of the triangle.

EXTENDED_NEIGHBOR_TRIANGLE()-
Returns all the triangles which share vertices with the vertices of the given triangle and sorts the array based on the insertion order of triangles.
          
INCIDENT_TRIANGLES()-
Returns an array of triangles that have the given vertex if it exists.


NEIGHBORS_OF_POINT()-
Returns an array of adjacent neighnors of the given point(if it exists)
          
EDGE_NEIGHBORS_OF_POINT()-
Returns an array of edge neighbors of the given point(if it exists) using the edgelist maintained in each point.
Time complexity=O(n) where n is number of vertices in the graph
 
 
IS_CONNECTED()-
checks if the given two triangles are connected.(it uses depth first traversal)
Returns true if the triangles are connected else false


TRIANGLE_NEIGHBOR_OF_EDGE()-
Returns an array of triangles which contain an edge similar to the given edge and sorts the array based on the insertion order of triangles.
 
MAXIMUM_DIAMETER()-
Used bft to find the maximum of all the shortest distances between any two triangles of a connected component which has maximum number of triangles. 
 
CENTROID()-
Returns an array of the average of all the vertices of each component.
          
CENTROID_OF_COMPONENT()-
Returns the average of all vertices of the component containing the given point.
 
CLOSEST_COMPONENTS()-
Returns the minimum distance between any two vertices of different components.

 

