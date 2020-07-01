//goal: a recognizable piece
//(recognizability in the eye of the beholder)

CSG topSphere = new Sphere(2).toCSG().movez(83)

CSG topDome = new Cylinder (5, 0, 2, (100)).toCSG().movez(80)

CSG outerCrown = new Cylinder (5, 7, 3, (16)).toCSG()
CSG innerCrown = new Cylinder (3.5, 5, 3, (16)).toCSG()
CSG crown = outerCrown.difference(innerCrown).movez(77.5);


CSG mainCylinder  = new Cylinder(7.5, 3, 80, (100)).toCSG()

CSG baseCylinder = new Cylinder(16, 6, 5, (100)).toCSG()
CSG lowestTurn = new RoundedCylinder(16, 3).cornerRadius(2).toCSG().movez(2.5)
CSG lowCone = new Cylinder(15, 5, 20, (100)).toCSG().movez(5.5)
CSG lowestRing = new RoundedCylinder(7, 4).cornerRadius(3).toCSG().movez(22)
CSG crownCylinder = new Cylinder(5, 5, 10, (100)).toCSG().movez(69)
CSG topCylinderRing = new RoundedCylinder(6, 3).cornerRadius(3).toCSG().movez(68)
CSG midCylinderRing = new Cylinder(8, 8, 1.5, (100)).toCSG().movez(65)
CSG botCylinderRing = new Cylinder(10, 10, 2, (100)).toCSG().movez(63)

CSG topAssembly = CSG.unionAll([crown, topDome, topSphere, crownCylinder, topCylinderRing, midCylinderRing, botCylinderRing])
CSG bottomPiece = CSG.unionAll([baseCylinder, mainCylinder, lowestTurn, lowCone, lowestRing])

CSG fullPiece = CSG.unionAll([bottomPiece, topAssembly])

fullPiece.setColor(javafx.scene.paint.Color.WHITE); 

return fullPiece

