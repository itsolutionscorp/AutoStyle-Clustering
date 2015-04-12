def compute(strand1, strand2)
		strand1 = strand1.split("")
		strand2 = strand2.split("")
		strand1.zip(strand2).count{ |x,y| x != y }
	end