def distance(seq1, seq2):
	distance = 0
	if len(seq1) != len(seq2):
		#The Hamming distance is only defined for sequences of equal length 
		#Does this mean you don't compare sequences of unequal length? 
		return 0

	return sum(x != y for x, y in zip(seq1, seq2))
