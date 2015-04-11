def distance(firstword, secondword):
	if len(firstword) == len(secondword):
		mismatches = 0
		for letter in range(0, len(firstword)):
			if firstword[letter] <> secondword[letter]:
				mismatches += 1 # increment counter when there's a mismatch 
		return mismatches
	else:
		return "Error: Word lengths must be equal"
