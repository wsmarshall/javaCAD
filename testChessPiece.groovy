//goal: black queen

CSG mainCylinder  = new Cylinder(7.5, 5, 70, (100)).toCSG()

CSG baseCylinder = new Cylinder(15, 6, 10, (100)).toCSG()

CSG fullPiece = CSG.unionAll([mainCylinder, baseCylinder])