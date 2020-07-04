//goal: a recognizable item
//(recognizability in the eye of the beholder)

double height = 85 //total height in mm of the piece
double width =  32 //total diameter of the base of the piece
double smallDistance = 0.0625 * width
double nearTopOfShape = 0.98 * height

//beginning of the top part, aka the 'head' of the piece
//sphere at the very top of the piece
CSG topSphere = new Sphere(smallDistance).toCSG().movez(nearTopOfShape)

//'hemispheric dome' that the top sphere rests on
CSG topDome = new Cylinder (width * 0.15625, 0, height* 0.0235, ((int) width * 4)).toCSG().movez(height * 0.94)

//outer part of the 'crown' piece
CSG outerCrown = new Cylinder (width * 0.15625, width * 0.21875, height * 0.035, ((int) width * 0.5)).toCSG()

//inner part of the 'crown' piece
CSG innerCrown = new Cylinder (width * 0.109, width * 0.15625, height * 0.035, ((int) width * 0.5)).toCSG()

//difference of the outer and inner, gives "crown shell"
CSG crown = outerCrown.difference(innerCrown).movez(height * 0.912);

//the cylinder that comes down from the crown and ends at the smallest ring
CSG crownCylinder = new Cylinder(width * 0.15625, width * 0.15625, height * 0.1176, ((int)width * 4)).toCSG().movez(height * 0.8117)

//located at the base of the crown cylinder, the smallest ring of the three
CSG topCylinderRing = new Cylinder(width * 0.1875, width * 0.09375).toCSG().movez(height * 0.8)

//below the 'head', the middle and middle sized ring of the three
CSG midCylinderRing = new Cylinder(width * 0.25, width * 0.25, height * 0.01765, ((int)width * 4)).toCSG().movez(height * 0.7647)

//below the middle ring, the largest ring of the three
CSG botCylinderRing = new Cylinder(width * 0.3125, width * 0.3125, height * 0.0235, ((int)width * 4)).toCSG().movez(height * 0.7412)

//beginning of the bottom part of the piece
//the 'spine' of the whole piece
CSG mainCylinder  = new Cylinder(width * 0.234, width * 0.09375, height * 0.9412, ((int)width * 4)).toCSG()

//very bottom cone of piece
CSG baseCylinder = new Cylinder(width * 0.5, width * 0.1875, height * 0.0588, ((int)width * 4)).toCSG()

//very bottom 'ring' - the 'crenelated' bezel just above the base
CSG lowestTurn = new RoundedCylinder(width * 0.5, width * 0.09375).cornerRadius(width * 0.0625).toCSG().movez(85 * 0.0294)

//'cone' piece that is visible/accessible to a hypothetical hand, near base (but not base)
CSG lowCone = new Cylinder(width * 0.46875, width * 0.15625, height * 0.2353, ((int)width * 4)).toCSG().movez(height * 0.0647)

//the 'crenelated bezel' that sits just above the lowCone
CSG lowConeTopRing = new RoundedCylinder(width * 0.2186, width * 0.125).cornerRadius(width * 0.0625).toCSG().movez(height * 0.2588)

//assembly of parts
//'head', aka top part of piece all gets put together
CSG topAssembly = CSG.unionAll([crown, topDome, topSphere, crownCylinder, topCylinderRing, midCylinderRing, botCylinderRing])

//bottom part all gets put together
CSG bottomPiece = CSG.unionAll([baseCylinder, mainCylinder, lowestTurn, lowCone, lowConeTopRing])

//top and bottom get put together
CSG fullPiece = CSG.unionAll([bottomPiece, topAssembly])

//set the color of the piece
fullPiece.setColor(javafx.scene.paint.Color.WHITE); 

return fullPiece
