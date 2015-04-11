def distance(strand1, strand2):
	#count of differences between two homologous DNA strands
	return sum([strand1[x] != strand2[x] for x in range(0, len(strand1))])
