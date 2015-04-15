def hamming(first, second):
	longer,shorter = first,second
	if len(second) > len(first):
		longer,shorter = second,first
	hamming_distance = 0
	for i in range(len(shorter)):
		if shorter[i] != longer[i]:
			hamming_distance += 1
	hamming_distance += (len(longer) - len(shorter))
	return hamming_distance
