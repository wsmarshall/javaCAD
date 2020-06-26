//goal: black queen

CSG topSphere = new Sphere(2).toCSG().movez(75)

CSG topDome = new Cylinder (5, 0, 5, (100)).toCSG().movez(70)

CSG mainCylinder  = new Cylinder(7.5, 5, 70, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 10, (100)).toCSG()

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder,topDome, topSphere])

fullPiece.setColor(javafx.scene.paint.Color.CYAN); //change this to black later

return fullPiece