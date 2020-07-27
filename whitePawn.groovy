//try at parameterizing an easier piece first
CSG makePawn(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 50.0, [120.0, 1.0])
	width = new LengthParameter("width of piece", 15.0, [100.0, 1.0])
	sphereSize = new LengthParameter("radius of top sphere", 8, [120.0, 1.0])
	squatFactor = new LengthParameter("how 'squished' the piece looks", 5, [110.0, 1.0])
	//computation of measurements from the parameters
	//refactor this - it's slowing the code down?
	/**
	sideRes = (int) height.getMM()*width.getMM()//how "rounded" the sides are (how many side 'facets' make up the side in question)
	mostlyAllHeight = (int) 0.90 * height.getMM()//mostly all the way up the piece
	mostlyHeight = (int) 0.8 * height.getMM()//most of the way up the piece
	aLittleHeight = (int) 0.2 * height.getMM()//only a little of the way up the piece
	mostlyWidth = (int) 0.8 * width.getMM()//most of the way through the piece
	someWidth = (int) 0.65 * width.getMM()//some of the way through the piece
	halfWidth = (int) 0.5 * width.getMM()//halfway through the piece
	aChunkOfWidth = (int) 0.3 * width.getMM()//only a little of the way through the piece
	*/
	//make the shapes that we need to combine
	sphere = new Sphere(sphereSize, sideRes, sideRes).toCSG().movez(mostlyAllHeight)//makes the head of the pawn, moves it up the piece
	mainCylinder = new Cylinder(someWidth, halfWidth, mostlyHeight, (sideRes)).toCSG() //cylinder that makes up the body, thins as it rises
	baseCylinder = new Cylinder (width.getMM(), aChunkOfWidth, aLittleHeight, (sideRes)).toCSG()//base conical cylinder
	//combine all the pieces to make the full piece
	CSG fullPiece = CSG.unionAll([baseCylinder, mainCylinder, sphere])
	//change the color to the desired shade
	fullPiece.setColor(javafx.scene.paint.Color.WHITE);
	
	return fullPiece
		.setRegenerate({makePawn()})//when parameters change, object re-renders to reflect user's specified change
	
}	
CSGDatabase.clear()//This is necessary to be able to change to default values when running from code
return makePawn();

/**
//try playing with the example first, then go to parameterizing the easier piece above
CSG makeCube(){
	//Set up some parameters to use
	xkey 		= new LengthParameter("X dimension",30,[120.0, 1.0])
	ykey 		= new LengthParameter("Y dimension",30,[130.0,2.0])
	zkey 		= new LengthParameter("Z dimension",30,[140.0,3.0])
	sphereSize 	= new LengthParameter("Sphere Size",30,[150.0,4.0])
	//you can also create parametrics that are not used in creating primitives
	offset	 	= new LengthParameter("Sphere Offset Distance",15,[20,-5])
	//build geometry with them
	cube = new Cube(xkey,ykey,zkey).toCSG()	//why def at beg. of line? seems to work without it
	sphere = new Sphere(sphereSize).toCSG()
	//apply moves based on the values in the parameters
	distance = xkey.getMM()/2-offset.getMM()+sphereSize.getMM()
	cube=cube.union(sphere.movex(distance))
	return cube
		.setParameter(offset)// add any parameters that are not used to create a primitive shape
		.setRegenerate({ makeCube()})// add a regeneration function to the CSG being returned to link a change event to a re-render
}
CSGDatabase.clear()//set up the database to force only the default values in	
//commenting out that database force mean I can't tweak values in the original code? why?	(what does the CSG database do?)
return makeCube();
*/
