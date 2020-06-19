/**
 * keep in mind the dimension numbers are 
 * radiuses along the x,y,z axes
 */



/**
ArrayList<Object> servoMeasurments = new ArrayList<Object>();

servoMeasurments.add(11.7) //servoThinDimentionThickness
servoMeasurments.add(23.6) // servoThickDimentionThickness
servoMeasurments.add(25.16) // servoShaftSideHeight
servoMeasurments.add(3.8) // outputShaftDiameter
servoMeasurments.add(5.13) //shaftToShortSideDistance
servoMeasurments.add(9.8) // shaftToShortSideFlandgeEdge
servoMeasurments.add(12.9) // tipOfShaftToBottomOfFlange
servoMeasurments.add(3.0 ) //flangeThickness
servoMeasurments.add(32.3) // flangeLongDimention
servoMeasurments.add(10.16) //bottomOfFlangeToTopOfBody

CSG servoReference =  (CSG)ScriptingEngine
	                    .gitScriptRun(
                                "https://gist.github.com/3f9fef17b23acfadf3f7.git", // git location of the library
	                              "servo.groovy" , // file to load
	                              servoMeasurments
                        )
// Create copy for additional links                      
CSG copyOfServo =   servoReference
			.clone()  
			.movex(40)
copyOfServo.setColor(javafx.scene.paint.Color.RED);

return [servoReference,
	copyOfServo
	]
*/

/**
CSG referencedThingy =  (CSG)ScriptingEngine
	                    .gitScriptRun(
                                "https://gist.github.com/d0a4fcda488a8958095b.git", // git location of the library
	                              "FactoryToBeLoaded.groovy" , // file to load
	                              null// no parameters (see next tutorial)
                        )
println "Loading factory CSG "+referencedThingy
return referencedThingy
*/

//return new Cube(20,40,80)
  //          .toCSG()

/**
double rad =2
def base = new Cube(20).toCSG()
			.difference(new Cube(5,10,20).toCSG())
			.difference(new Cube(10,5,20).toCSG())
			.rotz(5)
			.toZMin()
List<Polygon> polys = Slice.slice(base)
return [Fillet.outerFillet( base,(double)rad),polys]
*/

/**
import  eu.mihosoft.vrl.v3d.ext.quickhull3d.*
import eu.mihosoft.vrl.v3d.Vector3d

//Move and rotate opperations
double size =40;
CSG cube = new Cube(	size,// X dimension
			size,// Y dimension
			size//  Z dimension
			).toCSG()
CSG movedCube =  new Sphere(size).toCSG()
			.movex(-20)
			.movey(-40)
			.movez(60)
//This section is how you perform a shape of the "shrinkwrap" of the 2 shapes. 
CSG hulledCubes = cube.union(movedCube).hull();

//Alternate way to perform a Hull
hulledCubes = CSG.hullAll([cube,movedCube])
def points = [	new Vector3d(10, 50, 10),
			new Vector3d(10, 40, 10),
			new Vector3d(-10, 50, 10),
			new Vector3d(-10, 40, 10),
			new Vector3d(0, 50, 60)
]
CSG hullSection = HullUtil.hull(points)

return [cube,movedCube,hulledCubes.movex(size*2),hullSection]
 */

/**
//adding toManufacturing closure that will be called when you run 'to STL' or 'to SVG'
 // Make a cube and put it up in the air
CSG cubeInTheAir = new Cube(10).toCSG()
				.movez(30)
				.rotx(15)// rotate so it would not print well
cubeInTheAir.setName("CubeInTheAir")
cubeInTheAir.addExportFormat("stl")// make an stl of the object
cubeInTheAir.addExportFormat("svg")// make an svg of the object
cubeInTheAir.setManufacturing({ toMfg -> //this will be called when you run 'to STL' or 'to SVG'
	return toMfg
			.rotx(-15)// fix the orentation
			.toZMin()//move it down to the flat surface
})	
*/

/**
//TODO 
// Load an STL file from a git repo
// Loading a local file also works here
File servoFile = ScriptingEngine.fileFromGit(
	"https://github.com/NeuronRobotics/BowlerStudioVitamins.git",
	"BowlerStudioVitamins/stl/servo/smallservo.stl");
// Load the .CSG from the disk and cache it in memory
CSG servo  = Vitamins.get(servoFile);
String filename =ScriptingEngine.getWorkspace().getAbsolutePath()+"/CopiedStl.stl";
FileUtil.write(Paths.get(filename),
		servo.toStlString());
println "STL EXPORT to "+filename
return servo;
*/

/**
//TODO minkowski difference?

CSG startCube = new Cube(100).toCSG()
CSG cubeToFit = new Sphere(50/2).toCSG()
				.movez(startCube.getMaxZ())
				.setColor(javafx.scene.paint.Color.RED)
// This performs an intersection of the 2 parts, then minkowski the intersection,then cut the result from the base shape
// Use this function to make cut outs that will be 3d printed
CSG clearenceFit = startCube
				.minkowskiDifference(
					cubeToFit,// the part we want to fit into a cutout
					5.0// the offset distance to fit
					)
// To simply make a normal offset version of a given object us this function
// positive values makes a normal outset, and negative values makes a normal inset. 			
CSG clearenceObject = cubeToFit.toolOffset(5.0)
					.movey(100)
// To simply make a normal offset version of a given object us this function
// positive values makes a normal outset, and negative values makes a normal inset. 			
CSG clearenceObject2 = cubeToFit.toolOffset(-10.0)
					.movex(200)
										
CSG printNozzel = new Sphere(10/2).toCSG();
// Access the raw minkowski intermediates
ArrayList<CSG> mikObjs = cubeToFit.minkowski(printNozzel);
CSG remaining = cubeToFit;
for(CSG bit: mikObjs){
	remaining=remaining.intersect(bit);
}
remaining=remaining
			.movex(100)
mikObjs=mikObjs.collect{
	return it.movex(100).movey(100)			
}
mikObjs.addAll([clearenceFit,cubeToFit,clearenceObject,remaining,clearenceObject2])
return mikObjs
*/

/**
double size =40;
CSG cube = new Cube(	size,// X dimension
			size,// Y dimension
			size//  Z dimension
			).toCSG()
//create a sphere
CSG sphere = new Sphere(size/20*12.5).toCSG()
// perform an intersection
CSG cubePlusSphere = cube.intersect(sphere);

return [cubePlusSphere , cube.movex(size*1.5), sphere.movey(size*1.5)]
*/

/**
double size = 40;
CSG cube = new Cube(	size,// X dimention
			size,// Y dimention
			size//  Z dimention
			).toCSG()
//create a sphere
CSG sphere = new Sphere(size/20*12.5).toCSG()
// perform a difference
CSG cubePlusSphere = cube.difference(sphere);


return [cubePlusSphere , cube.movex(size*1.5), sphere.movey(size*1.5)]
*/

/**
double size = 40;
CSG cube = new Cube(	size,// X dimension
			size,// Y dimension
			size//  Z dimension
			).toCSG()
//create a sphere
CSG sphere = new Sphere(size/20*12.5).toCSG()
// perform a union
CSG cubePlusSphere = cube.union(sphere);

//To union a list of CSG's together use the static unionAll
CSG allUnion = CSG.unionAll([cubePlusSphere , cube.movex(size*1.5), sphere.movey(size*1.5)])

return allUnion
*/

/**
double size = 40;
CSG cube = new Cube(	size,// X dimension
			size,// Y dimension
			size//  Z dimension
			).toCSG()
			//Scale lets you increase or decrease the size by a scale factor
			
CSG cubeSmall = cube
		.scalex(0.5)
		.scaley(0.5)
		.scalez(0.5)
		.movey(size*1.5)
			
// this can be a shell or printer keepaway
// this increases the size by a specific measurment in mm
CSG cubeBigger = cube
		.makeKeepaway((double)10.0)
		.movex(size*1.5)
				
return [cube,cubeBigger, cubeSmall ]
*/

/**
//Move and rotate operations
double size =40;
CSG cube = new Cube(size,// X dimension
			size,// Y dimension
			size//  Z dimension
			).toCSG()
		
//cubeIntersectSphere = cubeIntersectSphere.move(x,y,z);// vector notation
CSG movedCube = cube
			.movex(50) //movement is of the center of the object
			.movey(50) //along the 3 specified axes
			.movez(50)
//rotate
//cubeIntersectSphere = cubeIntersectSphere.rot(rx,ry,rz);// vector notation
movedCube = movedCube //note the rotations are along the object's x, y, and z axes
			.rotx(45)
			.roty(45)
			.rotz(45)
//set colors
cube.setColor(javafx.scene.paint.Color.CYAN);
movedCube.setColor(javafx.scene.paint.Color.RED);

return [cube,movedCube]
*/

/**
return [ Parabola.extrudeByEquation(5,0.27,0,1)//first 3 are tk, 4th is height
	.move(20,0,0),//x, y, z
	Parabola.coneByEquation(5,1.27,0.7)
	.move(30,0,0),
	Parabola.coneByFocalLength(10, 10),
	Parabola.coneByHeight(10, 20)
		.move(50,0,0)
]
*/

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