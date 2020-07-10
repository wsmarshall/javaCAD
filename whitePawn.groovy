//try at parameterizing an easier piece first
CSG makepawn(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 30, [120.0, 1.0])
	width = new LengthParameter("width of piece", 10, [120.0, 1.0])
	sphereSize = new LengthParameter("radius of top sphere", 8, [120.0, 1.0])
}

return makeQueen();