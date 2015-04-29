def slices(sequence, length):
	subsequences = []
	if (len(sequence) < length or length == 0):
		raise ValueError("Subsequence length must be nonzero and not longer than string.")
	for start in range(len(sequence)-length+1):
		subsequence = [int(sequence[char]) for char in range(start, start+length)]
		subsequences.append(subsequence)
	return subsequences
