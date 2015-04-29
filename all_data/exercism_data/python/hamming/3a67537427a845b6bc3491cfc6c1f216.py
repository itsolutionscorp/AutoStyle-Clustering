def hamming(a, b):
	# use iterators in case of very long strings
	# iterate over both lists together
	# add the leftover from the longer string at the end
	
	distance = 0
	for (c_a, c_b) in zip(a, b):
		if c_a != c_b:
			distance += 1
			
	distance += abs(len(a) - len(b))
	
	return distance
	
