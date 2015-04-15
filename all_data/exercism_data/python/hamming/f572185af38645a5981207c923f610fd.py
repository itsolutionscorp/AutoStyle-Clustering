def distance(a_strand,b_strand):
	return sum(a != b for a, b in zip(a_strand, b_strand))
