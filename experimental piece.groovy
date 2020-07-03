//stretch goal - unsure if totally possible

double height = 85 //total height in mm of the piece
double width =  32 //total diameter of the base of the piece

//very bottom cone of piece
CSG baseCylinder = new Cylinder(width * 0.5, width * 0.1875, height * 0.0588, ((int)width * 4)).toCSG()

//very bottom 'ring' - the 'crenelated' bezel just above the base
CSG lowestTurn = new RoundedCylinder(width * 0.5, width * 0.09375).cornerRadius(width * 0.0625).toCSG().movez(85 * 0.0294)

//'cone' piece that is visible/accessible to a hypothetical hand, near base (but not base)
CSG lowCone = new Cylinder(width * 0.46875, width * 0.15625, height * 0.2353, ((int)width * 4)).toCSG().movez(height * 0.0647)

//the 'crenelated bezel' that sits just above the lowCone
CSG lowConeTopRing = new RoundedCylinder(width * 0.2186, width * 0.125).cornerRadius(width * 0.0625).toCSG().movez(height * 0.2588)

