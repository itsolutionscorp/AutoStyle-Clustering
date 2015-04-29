def to_rna(segment):
	# because of g,c,a multiple use going to do an intermediate step
	# could do a more complicated parsing, but this is simpler to me
	rna = segment.replace("A","U").replace("T","A")
	rna = rna.replace("C","X").replace("G","Z")
	rna = rna.replace("X","G").replace("Z","C")

	return rna
