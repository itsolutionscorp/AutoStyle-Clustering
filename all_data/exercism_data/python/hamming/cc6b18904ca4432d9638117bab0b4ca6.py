def distance(strand_1, strand_2):
	
	return sum(one != two for one, two in zip(strand_1, strand_2))
