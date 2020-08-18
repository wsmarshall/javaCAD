//goal: a recognizable item
//(recognizability in the eye of the beholder)

CSG makePiece(){
//set up parameters to use
	//default diameter of top sphere on head of piece
	sphereSize = new LengthParameter("diameter of the topmost sphere", 2, [10, 1])
	//default total height of the piece
	height = new LengthParameter("height of piece", 60, [120.0, 1.0])
	//width of the connecting spine that runs the entire length(height) of the piece
	coreWidth = new LengthParameter("diameter of the connecting spine", 1, [120, 1])
//computation of constants from parameters
	sideRes = (int) (30)//how rounded (faceted) the sides will be
	double aSmidge = 0.5//half a millimeter 
	double evenSmallestDistance = 2 * coreWidth.getMM()//only a small ways, as compared to the defined width. defaults to 2
	double smallestDistance = 3 * coreWidth.getMM() //slightly larger than a small way. defaults to 3
	double smallerDistance = 5 * coreWidth.getMM()//defaults to 5
	double smallDistance = 8 * coreWidth.getMM() //defaults to 8
	double distance = 10 * coreWidth.getMM() //defaults to 10
	double bigDistance = 15 * coreWidth.getMM() //defaults to 15
	double biggerDistance = 20 * coreWidth.getMM() //defaults to 20
	int width = 32 * coreWidth.getMM() //original 'width' measurement, defaults to 32
	int largerWidth = 64 * coreWidth.getMM() //wider than the width parameter, defaults to 64

	double fourFifthsHeight = 0.8 * height.getMM()
//make shapes to combine
	//beginning of the top part, or the "head" of the piece
		//the sphere at the very top
	CSG topSphere = new Sphere(sphereSize).toCSG()	
		.movez(height.getMM()) 
		//hemispheric dome that topSphere rests on
	CSG topDome =  new Cylinder (smallerDistance, evenSmallestDistance, evenSmallestDistance, (sideRes)).toCSG()
		.movez((height.getMM() - smallestDistance))
		//outer part of the 'crown' piece
	CSG outerCrown = new Cylinder(smallerDistance, smallestDistance + smallerDistance, smallestDistance, ((int) smallDistance)).toCSG()
		//inner part of the 'crown' piece
	CSG innerCrown = new Cylinder(smallestDistance + aSmidge, smallerDistance, smallestDistance, ((int) smallDistance)).toCSG()
		//difference of the outer and inner, gives "crown shell"
	CSG crown = outerCrown.difference(innerCrown).movez(height.getMM() - smallerDistance);
		//the cylinder that comes down from the crown and ends at the smallest ring
	CSG crownCylinder = new Cylinder(smallerDistance, smallerDistance, distance, (sideRes)).toCSG()
		.movez(height.getMM() - bigDistance)
		//located at the base of the crown cylinder, the smallest ring of the three
	CSG topCylinderRing = new Cylinder (smallestDistance+smallestDistance, smallestDistance + smallestDistance, evenSmallestDistance, (sideRes)).toCSG()
		.movez(fourFifthsHeight + evenSmallestDistance)
		//below the 'head', the middle and middle sized ring of the three
	CSG midCylinderRing = new Cylinder(smallDistance, smallDistance, evenSmallestDistance, (sideRes)).toCSG()	
		.movez((fourFifthsHeight - evenSmallestDistance))
		//below the middle ring, the largest ring of the three
	CSG botCylinderRing = new Cylinder(distance, distance, evenSmallestDistance, (sideRes)).toCSG()
		.movez((fourFifthsHeight - smallestDistance))
	//beginning of the bottom part, or the base & body of the piece
		//runs the length of the piece, allows user to access height as a UI parameter via slider
	CSG connectingSpine = new Cylinder(coreWidth, coreWidth, height, (sideRes)).toCSG() 
		//the 'spine' of the whole piece
	CSG mainCylinder  = new Cylinder(smallDistance, smallestDistance, fourFifthsHeight, (sideRes)).toCSG()
		//very bottom cone of piece
	CSG baseCylinder = new Cylinder(bigDistance, evenSmallestDistance, bigDistance, (sideRes)).toCSG()
		//very bottom 'ring' - the 'crenelated' bezel just above the base
	CSG lowestTurn = new RoundedCylinder(bigDistance, smallestDistance).cornerRadius(evenSmallestDistance).toCSG()
		.movez(smallestDistance)
		//'cone' piece that is visible/accessible to a hypothetical hand, near base (but not base)
	CSG lowCone = new Cylinder(distance, smallerDistance, biggerDistance, (sideRes)).toCSG()
		.movez(smallestDistance + smallestDistance)
		//the 'crenelated bezel' that sits just above the lowCone
	CSG lowConeTopRing = new RoundedCylinder(smallDistance, evenSmallestDistance).cornerRadius(evenSmallestDistance).toCSG().movez(biggerDistance + evenSmallestDistance)
	//combine top part
	CSG topAssembly = CSG.unionAll([topSphere, topDome, crown, crownCylinder, topCylinderRing, midCylinderRing, botCylinderRing])
	
	//combine bottom part
	CSG bottomPiece = CSG.unionAll([connectingSpine, mainCylinder, baseCylinder, lowestTurn, lowCone, lowConeTopRing])
	
	//combine both top and bottom parts to make the full piece
	CSG fullPiece = CSG.unionAll([topAssembly, bottomPiece])
	//change the color to the desired shade
	fullPiece.setColor(javafx.scene.paint.Color.DIMGRAY);

	return fullPiece
		.setRegenerate({makePiece()})//when parameters change, object rerenders to reflect user-specified changes

}
CSGDatabase.clear()//necessary in order to change default values when running from code
return makePiece();