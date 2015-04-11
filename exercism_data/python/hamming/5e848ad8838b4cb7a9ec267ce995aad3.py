def distance(s1, s2):
	#count of differences between two homologous DNA strands
	return sum([a != b for a, b in zip(s1,s2)])
