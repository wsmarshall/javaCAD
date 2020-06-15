
//this shape cannot be smaller than the default that it was forked from?!
return new Cube(	200,// X dimensional radius, in 1 mm increments
			200,// Y dimension radius, in 1 mm increments
			200//  Z dimension radius, in 1 mm increments
			).toCSG()// this converts from the geometry to an object we can work with
