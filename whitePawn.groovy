//goal: whitePawn

CSG mainCylinder  = new Cylinder(7.5, 5, 40, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 10, (100)).toCSG()

CSG sphere = new Sphere(50).toCSG()
	.movez(40)

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder])