def distance(a_strand,b_strand):
	iterator = zip(a_strand, b_strand)
	return sum(a != b for a, b in iterator)
