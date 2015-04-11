# hamming.py

def distance(original, mutation):
	# initialize variables
	distance = 0
	position = 0

	# error if strings don't have equal length
	if len(original) != len(mutation):
		return 'Input error. Strand lengths must be equal.'
	else:
		# operate as many times as inputs have positions
		for positions in original:
			# increment distance for each unequal position
			if (original[position] != mutation[position]):
				distance += 1
				position += 1
			# do not increment for equal position
			else:
				position += 1
	
	return distance
