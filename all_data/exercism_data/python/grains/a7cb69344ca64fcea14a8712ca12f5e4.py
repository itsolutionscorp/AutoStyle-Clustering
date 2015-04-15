grains = {1:1}

def on_square(x):
	p = x - 1
	if not grains.get(x):
		grains[x] = 2**p
	return grains[x]

def total_after(x):
	print grains
	return sum([on_square(i) for i in xrange(1, x+1)]) 
