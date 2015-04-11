def distance(astrand, bstrand):
	if len(astrand) == len(bstrand):
		return sum([1 for a, b in zip(astrand, bstrand) if a!=b])
	else:
		raise ValueError('Sequences must have same length.')
