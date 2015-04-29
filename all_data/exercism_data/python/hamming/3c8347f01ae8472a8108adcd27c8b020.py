def distance(strand1, strand2):
	#count of differences between two homologous DNA strands
	return sum([1 if strand1[x] != strand2[x] else 0 for x in range(0, len(strand1))])
