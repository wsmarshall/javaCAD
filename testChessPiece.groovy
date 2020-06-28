//goal: black queen

CSG topSphere = new Sphere(2).toCSG().movez(73)

CSG topDome = new Cylinder (5, 0, 2, (100)).toCSG().movez(70)

//need 2 polygons, 12 sided? dif of smaller from bigger for 'crown' piece
CSG outerCrown = new Cylinder (5, 7, 3, (16)).toCSG()
CSG innerCrown = new Cylinder (3.5, 5, 3, (16)).toCSG()
CSG crown = outerCrown.difference(innerCrown).movez(67);


CSG mainCylinder  = new Cylinder(7.5, 3, 70, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 5, (100)).toCSG()
CSG lowestTurn = 

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder,crown, topDome, topSphere])

fullPiece.setColor(javafx.scene.paint.Color.CYAN); //change this to black later

return fullPiece