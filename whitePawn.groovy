//try at parameterizing an easier piece first
/**
CSG makePawn(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 30, [120.0, 1.0])
	width = new LengthParameter("width of piece", 10, [100.0, 1.0])
	sphereSize = new LengthParameter("radius of top sphere", 100, [120.0, 1.0])
	squatFactor = new LengthParameter("how 'squished' the piece looks", 5, [110.0, 1.0])
	//make the shapes that we need to combine
	sphere = new Sphere(sphereSize).toCSG()

	return [sphere]
	
}

return makePawn();
*/

//try playing with the example first, then go to parameterizing the easier piece above
CSG makeCube(){
	//Set up some parameters to use
	xkey 		= new LengthParameter("X dimention",30,[120.0, 1.0])//what's the format for the LengthParameter?
	ykey 		= new LengthParameter("Y dimention",30,[130.0,2.0])//no time today, try again tomorrow
	zkey 		= new LengthParameter("Z dimention",30,[140.0,3.0])
	sphereSize 	= new LengthParameter("Sphere Size",30,[150.0,4.0])
	//you can also create parametrics that are not used in creating primitives
	offset	 	= new LengthParameter("Sphere Offset Distance",15,[20,-5])
	//build geometry with them
	def cube = new Cube(xkey,ykey,zkey).toCSG()	
	sphere = new Sphere(sphereSize).toCSG()
	//apply moves based on the values in the parameters
	distance = xkey.getMM()/2-offset.getMM()+sphereSize.getMM()
	cube=cube.union(sphere.movex(distance))
	return cube
		.setParameter(offset)// add any parameters that are not used to create a primitive
		.setRegenerate({ makeCube()})// add a regeneration function to the CSG being returrned to lonk a change event to a re-render
}
//CSGDatabase.clear()//set up the database to force only the default values in			
return makeCube();