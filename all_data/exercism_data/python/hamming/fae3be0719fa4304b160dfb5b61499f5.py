def distance(sequenceA, sequenceB):
	if not len(sequenceA) == len(sequenceB):
		return 'Input sequences must have the same amount of nucleotides.'

	diff_count = 0
	for index, x in enumerate(sequenceA):
		if x != sequenceB[index]:
			diff_count += 1
	
	return diff_count
