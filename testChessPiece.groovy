//goal: a recognizable item
//(recognizability in the eye of the beholder)

CSG makePiece(){
	//set up parameters to use
	height = new LengthParameter("height of piece", 85, [120.0, 1.0])//default total height of the piece
	width = new LengthParameter("width of piece", 32, [120.0, 1.0])//default total diameter of the base of the piece
	sphereSize = new LengthParameter("diameter of the topmost sphere", 2, [10, 0.5])//default diameter of top sphere on head of piece
	//computation of constants from parameters
	sideRes = (int) (height.getMM() * width.getMM())//how rounded (faceted) the sides will be
	double smallestDistance = 0.0625 * width.getMM() //only a small ways, as compared to the defined width. defaults to 2
	double smallerDistance = 0.15625 * height.getMM()//defaults to 5
	double smallDistance = 0.25 * width.getMM() //defaults to 8
	double distance = 0.3125 * width.getMM() //defaults to 10
	double bigDistance = 0.46875 * width.getMM() //defaults to 15
	double biggerDistance = 0.625 * width.getMM() //defaults to 20
	//is this enough constants? let's find out!
	//make shapes to combine
		//beginning of the top part, or the "head" of the piece
	CSG topSphere = new Sphere(sphereSize).toCSG().movez(nearTop) //the sphere at the very top
	//CSG topDome =  new Cylinder ()
	//combine top part
	//combine bottom part
	//combine both top and bottom parts to make the full piece
	CSG fullPiece = CSG.unionAll([])
	//change the color to the desired shade
	fullPiece.setColor(javafx.scene.paint.Color.DIMGRAY);

	return fullPiece
		.setRegenerate({makePiece()})//when parameters change, object rerenders to reflect user-specified changes
	
}
CSGDatabase.clear()//necessary in order to change default values when running from code
return makePiece();

/**

//'hemispheric dome' that the top sphere rests on
CSG topDome = new Cylinder (width * 0.15625, 0, height* 0.0235, ((int) width * 4)).toCSG().movez(height * 0.94)
CSG topDome = new Cylinder (5, 0, 2, (100)).toCSG().movez(80)

//outer part of the 'crown' piece
CSG outerCrown = new Cylinder (width * 0.15625, width * 0.21875, height * 0.035, ((int) width * 0.5)).toCSG()
CSG outerCrown = new Cylinder (5, 7, 3, (16)).toCSG()

//inner part of the 'crown' piece
CSG innerCrown = new Cylinder (width * 0.109, width * 0.15625, height * 0.035, ((int) width * 0.5)).toCSG()
CSG innerCrown = new Cylinder (3.5, 5, 3, (16)).toCSG()

//difference of the outer and inner, gives "crown shell"
CSG crown = outerCrown.difference(innerCrown).movez(height * 0.912);
CSG crown = outerCrown.difference(innerCrown).movez(77.5);

//the cylinder that comes down from the crown and ends at the smallest ring
CSG crownCylinder = new Cylinder(width * 0.15625, width * 0.15625, height * 0.1176, ((int)width * 4)).toCSG().movez(height * 0.8117)
CSG crownCylinder = new Cylinder(5, 5, 10, (100)).toCSG().movez(69)

//located at the base of the crown cylinder, the smallest ring of the three
CSG topCylinderRing = new Cylinder(width * 0.1875, width * 0.09375).toCSG().movez(height * 0.8)
CSG topCylinderRing = new RoundedCylinder(6, 3).cornerRadius(3).toCSG().movez(68)

//below the 'head', the middle and middle sized ring of the three
CSG midCylinderRing = new Cylinder(width * 0.25, width * 0.25, height * 0.01765, ((int)width * 4)).toCSG().movez(height * 0.7647)
CSG midCylinderRing = new Cylinder(8, 8, 1.5, (100)).toCSG().movez(65)

//below the middle ring, the largest ring of the three
CSG botCylinderRing = new Cylinder(width * 0.3125, width * 0.3125, height * 0.0235, ((int)width * 4)).toCSG().movez(height * 0.7412)
CSG botCylinderRing = new Cylinder(10, 10, 2, (100)).toCSG().movez(63)

//beginning of the bottom part of the piece
//the 'spine' of the whole piece
CSG mainCylinder  = new Cylinder(width * 0.234, width * 0.09375, height * 0.9412, ((int)width * 4)).toCSG()
CSG mainCylinder  = new Cylinder(7.5, 3, 80, (100)).toCSG()

//very bottom cone of piece
CSG baseCylinder = new Cylinder(width * 0.5, width * 0.1875, height * 0.0588, ((int)width * 4)).toCSG()
CSG baseCylinder = new Cylinder(16, 6, 5, (100)).toCSG()

//very bottom 'ring' - the 'crenelated' bezel just above the base
CSG lowestTurn = new RoundedCylinder(width * 0.5, width * 0.09375).cornerRadius(width * 0.0625).toCSG().movez(85 * 0.0294)
CSG lowestTurn = new RoundedCylinder(16, 3).cornerRadius(2).toCSG().movez(2.5)

//'cone' piece that is visible/accessible to a hypothetical hand, near base (but not base)
CSG lowCone = new Cylinder(width * 0.46875, width * 0.15625, height * 0.2353, ((int)width * 4)).toCSG().movez(height * 0.0647)
CSG lowCone = new Cylinder(15, 5, 20, (100)).toCSG().movez(5.5)

//the 'crenelated bezel' that sits just above the lowCone
CSG lowConeTopRing = new RoundedCylinder(width * 0.2186, width * 0.125).cornerRadius(width * 0.0625).toCSG().movez(height * 0.2588)

//assembly of parts
//'head', aka top part of piece all gets put together
CSG topAssembly = CSG.unionAll([crown, topDome, topSphere, crownCylinder, topCylinderRing, midCylinderRing, botCylinderRing])

//bottom part all gets put together
CSG bottomPiece = CSG.unionAll([baseCylinder, mainCylinder, lowestTurn, lowCone, lowConeTopRing])

//top and bottom get put together
CSG fullPiece = CSG.unionAll([bottomPiece, topAssembly])

*/