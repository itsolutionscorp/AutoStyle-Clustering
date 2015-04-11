def distance(original, mutant):
	# First, test that the strings are equal length
	hamming_count = 0
	if len(original) == len(mutant):
		for protine in range(len(original)):
			if original[protine] != mutant[protine]:
				hamming_count += 1
	return hamming_count
