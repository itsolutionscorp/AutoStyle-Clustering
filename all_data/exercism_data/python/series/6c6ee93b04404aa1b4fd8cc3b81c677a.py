# return the input string's subsequences of a given length
def slices(sequence, length):
	subsequences = []

	if (len(sequence) < length or length == 0):
		raise ValueError("Subsequence length must be nonzero and not longer than string.")

	# build a list of all sublists, where a sublist is a sequence of characters
	# (which are for unknown reasons required by the tests to be integers)
	for start in range(len(sequence)-length+1):
		subsequence = [int(sequence[char]) for char in range(start, start+length)]
		subsequences.append(subsequence)

	return subsequences
