def distance(first, second):
	x = 0
	for i in range(0, len(first)):
		if first[i] <> second[i]:
			x = x + 1 # increment counter when there's a mismatch 
	return x
