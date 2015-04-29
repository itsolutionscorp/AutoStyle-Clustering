import operator

def distance(dna1,dna2):
	return sum(map(operator.ne,dna1,dna2))
