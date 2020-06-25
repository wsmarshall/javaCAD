//goal: black queen

CSG topSphere = new Sphere(2).toCSG().movez(70)

CSG mainCylinder  = new Cylinder(7.5, 5, 70, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 10, (100)).toCSG()

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder, topSphere])

fullPiece.setColor(javafx.scene.paint.Color.CYAN); //change this to black later

return fullPiece