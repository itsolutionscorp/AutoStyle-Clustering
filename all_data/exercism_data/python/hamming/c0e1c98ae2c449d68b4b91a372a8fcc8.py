from operator import ne

def distance(dna1,dna2):
	return sum(map(ne,dna1,dna2))
