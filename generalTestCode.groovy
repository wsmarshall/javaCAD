/**
 * keep in mind the dimension numbers are 
 * radiuses along the x,y,z axes
 */

return [ Parabola.extrudeByEquation(5,0.27,0,1)
	.move(20,0,0),
	Parabola.coneByEquation(5,1.27,0.7)
	.move(30,0,0),
	Parabola.coneByFocalLength(10, 10),
	Parabola.coneByHeight(10, 20)
		.move(50,0,0)
]

/**
//triangular wedges
return [
new Wedge(60,25,40).toCSG().movey(75),//x, y, z
new Isosceles(60,25,40).toCSG()
]
*/

//return new ChamferedCylinder(50,60.0,10).toCSG()//radius, height, chamfer (facet?) height

/**
double size =20 //this is the distance from the center to the vertices
return [
new Dodecahedron(size).toCSG().movex(size*0),
new Icosahedron(size).toCSG().movex(size*2),
new Octahedron(size).toCSG().movex(size*4),
new Tetrahedron(size).toCSG().movex(size*6),
]
*/

/**
//create an extruded polygon
CSG polygon = Extrude.points(new Vector3d(0, 0, 4),//x shift of top face, y shift of top face, extrusion depth (height)
                new Vector3d(0,0),// All values after and including this are the points in the polygon
                new Vector3d(80,0),// Bottom right corner
                new Vector3d(60,40),// upper right corner
                new Vector3d(20,40)// upper left corner
        );
return polygon
*/

/**
CSG roundedCylinder = new RoundedCylinder(50, 60.0)
                                .cornerRadius(10)// sets the radius of the corner
                                .toCSG()// converts it to a CSG tor display

return roundedCylinder
*/

/**
CSG simpleSyntax =new Cylinder(10,40).toCSG() // a one line Cylinder, 16 sided by default

//create a Cylinder with circular cross section and 'flaring' from base to top
CSG myCylinder = new Cylinder(10, // Radius at the bottom
                      		20, // Radius at the top
                      		40, // Height
                      		(int)300 //resolution
                      		).toCSG()//convert to CSG to display
                      		.movey(50)
  //create a Cylinder with square pyramidal shape
CSG pyramid = new Cylinder(20, // Radius at the bottom
                      		0, // Radius at the top
                      		40, // Height
                      		(int)4 //resolution: this is the 'number of sides'
                      		).toCSG()//convert to CSG to display                    			 
                      		.movex(50)
   //create a Cylinder with regular hexagonal cross section
CSG hex = new Hexagon(20, // Flat to flat radius
                      		40 // Height
                      		).toCSG()//convert to CSG to display                    			 
                      		.movex(50)
                      		.movey(50)
 return [simpleSyntax,myCylinder ,pyramid,hex]
*/

/**
//create a sphere with panelled facets
CSG sphere = new Sphere(25)// Spheres radius
				.toCSG()// convert to CSG to display
				.movex(100)

//create a new sphere with defined surface
CSG sphereHighRes = new Sphere(25,// Spheres radius
						40,//elevation Divisions
						40)// vertical divisions
						.toCSG()// convert to CSG to display
//note: upper limit on elevation & vertical divisions? c/n get 'smooth' sphere
return [sphere,sphereHighRes]
*/

//new ChamferedCube(200,100,50,15).toCSG()

/**
//create a rounded cube
CSG roundedCube = new RoundedCube(	40,// X dimension
				40,// Y dimension
				40//  Z dimension
				)
				.cornerRadius(4)// sets the radius of the corner
				.toCSG()// converts it to a CSG to display
return roundedCube
*/