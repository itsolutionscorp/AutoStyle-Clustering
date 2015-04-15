def ham_val(cs):
	return 0 if cs[0] == cs[1] else 1

def distance(dna1, dna2):
	return sum(map(ham_val, zip(dna1, dna2)))
