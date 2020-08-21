//try at parameterizing an easier piece first
CSG makePawn(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 50.0, [120.0, 1.0]) //height of the piece
	widthAtBottom = new LengthParameter("width of piece", 10.0, [100.0, 1.0]) //width at bottom of the piece
	widthAtTop = new LengthParameter("width at top of piece", 6, [50, 0.5])  // width at top of the piece (how much the piece "narrows")
	sphereSize = new LengthParameter("radius of top sphere", 8, [120.0, 1.0]) //sphere at top of piece
	//computation of measurements from the parameters
	sideRes = (int) (30)//how faceted (rounded on the sides) the pieces will be 
	widerWidth = (1.5 * widthAtBottom.getMM()) //wider than the default width at bottom
	lesserWidth = (0.5 * widthAtTop.getMM()) // smaller than the default width at top 
	lesserHeight = (0.3 * height.getMM()) //shorter than default height
	//make the shapes that we need to combine
	sphere = new Sphere(sphereSize, sideRes, sideRes).toCSG().movez(height.getMM())//makes the head of the pawn, moves it up the piece
	mainCylinder = new Cylinder(widthAtBottom, widthAtTop, height, (sideRes)).toCSG() //cylinder that makes up the body, thins as it rises
	baseCylinder = new Cylinder (widerWidth, lesserWidth, lesserHeight, (sideRes)).toCSG()//base conical cylinder
	//combine all the pieces to make the full piece
	CSG fullPiece = CSG.unionAll([baseCylinder, mainCylinder, sphere])
	//change the color to the desired shade
	fullPiece.setColor(javafx.scene.paint.Color.WHITE);
	
	return fullPiece
		.setRegenerate({makePawn()})//when parameters change, object re-renders to reflect user's specified change
	
}	
CSGDatabase.clear()//This is necessary to be able to change to default values when running from code
return makePawn();