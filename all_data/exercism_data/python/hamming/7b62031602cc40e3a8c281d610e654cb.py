def hamming(DNA1, DNA2):
	if DNA1 == DNA2:
		return 0

	# track differences
	difference = 0

	DNA1len=len(DNA1)
	DNA2len=len(DNA2)
	# strings in python are lists of characters
	for char in range(min(DNA1len,DNA2len)):
		# Compare up to the shortest of the 2 DNA strings
		if DNA1[char] != DNA2[char]:
			difference += 1

	# Add in the difference in length of the 2 DNA strings
	difference += abs(DNA1len - DNA2len)

	return difference