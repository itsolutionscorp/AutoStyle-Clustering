def distance(astrand, bstrand):
	d = 0
	for a, b, in zip(astrand, bstrand):
		if a!=b:
			d+=1
	return d
