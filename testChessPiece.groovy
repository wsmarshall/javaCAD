//goal: black queen

CSG topSphere = new Sphere(2).toCSG().movez(83)

CSG topDome = new Cylinder (5, 0, 2, (100)).toCSG().movez(80)

//need 2 polygons, 12 sided? dif of smaller from bigger for 'crown' piece
CSG outerCrown = new Cylinder (5, 7, 3, (16)).toCSG()
CSG innerCrown = new Cylinder (3.5, 5, 3, (16)).toCSG()
CSG crown = outerCrown.difference(innerCrown).movez(77.5);


CSG mainCylinder  = new Cylinder(7.5, 3, 80, (100)).toCSG()

CSG baseCylinder = new Cylinder(16, 6, 5, (100)).toCSG()
CSG lowestTurn = new RoundedCylinder(16, 3).cornerRadius(2).toCSG().movez(2.5)
CSG lowCone = new Cylinder(15, 5, 10, (100)).toCSG().movez(5.5)
CSG lowestRing = new RoundedCylinder(8, 4).cornerRadius(3).toCSG().movez(12)

CSG topAssembly = CSG.unionAll([crown, topDome, topSphere])
CSG bottomPiece = CSG.unionAll([baseCylinder, lowestTurn, lowCone, lowestRing, mainCylinder])

CSG fullPiece = CSG.unionAll([bottomPiece, topAssembly])

fullPiece.setColor(javafx.scene.paint.Color.CYAN); //change this to black later

return fullPiece