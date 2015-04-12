def compute(a, b)
		a.split(//).zip(b.split(//)).map{ |x,y| x == y }.count(false)
	end