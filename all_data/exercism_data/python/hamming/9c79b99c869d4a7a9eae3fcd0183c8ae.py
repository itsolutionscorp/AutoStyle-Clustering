def distance(a_strand,b_strand):
	count = 0
	for a, b in zip(a_strand, b_strand):
		if a != b: count += 1
	return count
