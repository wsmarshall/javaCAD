//goal: a recognizable item
//(recognizability in the eye of the beholder)

//TODO code is stalling ("running out of heap space") fix the problems -- how?

CSG makePiece(){
//set up parameters to use
	//default total height of the piece
	height = new LengthParameter("height of piece", 85, [120.0, 1.0])
	//default total diameter of the base of the piece
	width = new LengthParameter("width of piece", 32, [120.0, 1.0])
	//default diameter of top sphere on head of piece
	sphereSize = new LengthParameter("diameter of the topmost sphere", 2, [10, 0.5])
//computation of constants from parameters
	sideRes = (int) (height.getMM() * width.getMM())//how rounded (faceted) the sides will be
	double aSmidge = 0.5//half a millimeter 
	double evenSmallestDistance = 0.0625 * width.getMM()//only a small ways, as compared to the defined width. defaults to 2
	double smallestDistance = 0.09375 * width.getMM() //slightly larger than a small way. defaults to 3
	double smallerDistance = 0.15625 * height.getMM()//defaults to 5
	double smallDistance = 0.25 * width.getMM() //defaults to 8
	double distance = 0.3125 * width.getMM() //defaults to 10
	double bigDistance = 0.46875 * width.getMM() //defaults to 15
	double biggerDistance = 0.625 * width.getMM() //defaults to 20
	int largerWidth = 4 * width.getMM() //wider than the width parameter
	double halfWidth = 0.5 * width.getMM() // half of the width parameter

	double fourFifthsHeight = 0.8 * height.getMM()
//make shapes to combine
	//beginning of the top part, or the "head" of the piece
		//the sphere at the very top
	CSG topSphere = new Sphere(sphereSize).toCSG()	
		.movez(height.getMM() - smallestDistance) 
		//hemispheric dome that topSphere rests on
	CSG topDome =  new Cylinder (smallerDistance, 0, evenSmallestDistance, (largerWidth)).toCSG()
		.movez((height.getMM() - smallestDistance)) //TODO problem with the arguments in the movez 
		//outer part of the 'crown' piece
	CSG outerCrown = new Cylinder(smallerDistance, smallestDistance + smallerDistance, smallestDistance, ((int) halfWidth)).toCSG()
		//inner part of the 'crown' piece
	CSG innerCrown = new Cylinder(smallestDistance + aSmidge, smallerDistance, smallestDistance, ((int) halfWidth)).toCSG()
		//difference of the outer and inner, gives "crown shell"
	CSG crown = outerCrown.difference(innerCrown).movez(height.getMM() - smallDistance);
		//the cylinder that comes down from the crown and ends at the smallest ring
	CSG crownCylinder = new Cylinder(smallerDistance, smallerDistance, distance, (sideRes)).toCSG()
		.movez(height.getMM() - biggerDistance + smallerDistance)
		//located at the base of the crown cylinder, the smallest ring of the three
	CSG topCylinderRing = new Cylinder (smallestDistance + smallestDistance, smallestDistance).toCSG()
		.movez(fourFifthsHeight)
		//below the 'head', the middle and middle sized ring of the three
	CSG midCylinderRing = new Cylinder(smallDistance, smallDistance, evenSmallestDistance, (sideRes)).toCSG()	
		.movez((fourFifthsHeight - evenSmallestDistance))
		//below the middle ring, the largest ring of the three
	CSG botCylinderRing = new Cylinder(distance, distance, evenSmallestDistance, (sideRes)).toCSG()
		.movez((fourFifthsHeight - smallerDistance - evenSmallestDistance))
		
	//beginning of the bottom part, or the base & body of the piece
		//runs the length of the piece, allows user to access height as a UI parameter via slider
	CSG connectingSpine = new Cylinder(sphereSize, sphereSize, height, (sideRes)).toCSG() 
		//the 'spine' of the whole piece
	CSG mainCylinder  = new Cylinder(smallDistance, smallestDistance, fourFifthsHeight, (sideRes)).toCSG()
		//very bottom cone of piece
	CSG baseCylinder = new Cylinder(width, sphereSize, sphereSize, (sideRes)).toCSG()
		//very bottom 'ring' - the 'crenelated' bezel just above the base
	CSG lowestTurn = new RoundedCylinder(halfWidth, smallestDistance).cornerRadius(evenSmallestDistance).toCSG()
		.movez(smallestDistance)
		//'cone' piece that is visible/accessible to a hypothetical hand, near base (but not base)
	CSG lowCone = new Cylinder(bigDistance, smallerDistance, biggerDistance, (sideRes)).toCSG()
		.movez(height.getMM() + smallestDistance + smallestDistance)
		//the 'crenelated bezel' that sits just above the lowCone
	CSG lowConeTopRing = new RoundedCylinder(smallDistance, smallerDistance).cornerRadius(evenSmallestDistance).toCSG().movez(biggerDistance + evenSmallestDistance)
	
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