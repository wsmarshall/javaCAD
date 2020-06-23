//goal: whitePawn

CSG mainCylinder  = new Cylinder(7.5, 5, 40, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 10, (100)).toCSG()

CSG sphere = new Sphere(7.75, 100, 100).toCSG()
	.movez(40)

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder, sphere])

fullPiece.setColor(javafx.scene.paint.Color.WHITE);

return fullPiece