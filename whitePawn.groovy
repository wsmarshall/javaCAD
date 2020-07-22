//try at parameterizing an easier piece first
CSG makePawn(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 30, [120.0, 1.0])
	width = new LengthParameter("width of piece", 10, [100.0, 1.0])
	sphereSize = new LengthParameter("radius of top sphere", 8, [120.0, 1.0])
	squatFactor = new LengthParameter("how 'squished' the piece looks", 5, [110.0, 1.0])
	//make the shapes that we need to combine
	sphere = new Sphere(sphereSize).toCSG()

	return sphere
		.setRegenerate({makePawn()})//when parameters change, object re-renders to reflect user's specified change
	
}
CSGDatabase.clear()
return makePawn();

/**
//try playing with the example first, then go to parameterizing the easier piece above
CSG makeCube(){
	//Set up some parameters to use
	xkey 		= new LengthParameter("X dimension",30,[120.0, 1.0])//what's the format for the LengthParameter?
	ykey 		= new LengthParameter("Y dimension",30,[130.0,2.0])
	zkey 		= new LengthParameter("Z dimension",30,[140.0,3.0])
	sphereSize 	= new LengthParameter("Sphere Size",30,[150.0,4.0])
	//you can also create parametrics that are not used in creating primitives
	offset	 	= new LengthParameter("Sphere Offset Distance",15,[20,-5])
	//build geometry with them
	cube = new Cube(xkey,ykey,zkey).toCSG()	//why def at beg. of line? seems to work without it
	sphere = new Sphere(sphereSize).toCSG()
	//apply moves based on the values in the parameters
	distance = xkey.getMM()/2-offset.getMM()+sphereSize.getMM()//getMM() returns the LengthParameter's numerical value in millimeters, I think
	cube=cube.union(sphere.movex(distance))
	return cube
		.setParameter(offset)// add any parameters that are not used to create a primitive
		.setRegenerate({ makeCube()})// add a regeneration function to the CSG being returned to link a change event to a re-render
}
CSGDatabase.clear()//set up the database to force only the default values in	
//commenting out that database force mean I can't tweak values in the original code? why?	(what does the CSG database do?)
return makeCube();
*/