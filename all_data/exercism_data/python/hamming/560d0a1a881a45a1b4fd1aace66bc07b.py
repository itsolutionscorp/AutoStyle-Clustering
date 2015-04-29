def hamming(sequence_1, sequence_2):
	length = min(len(sequence_1), len(sequence_2))
	hamming_distance = abs(len(sequence_1) - len(sequence_2))
	index = 0
	while index < length:
		first = sequence_1[index]
		second = sequence_2[index]
		if first is not second:
			hamming_distance += 1
		index += 1
			
	return hamming_distance
