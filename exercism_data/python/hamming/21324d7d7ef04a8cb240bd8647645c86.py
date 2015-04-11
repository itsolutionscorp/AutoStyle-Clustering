def distance(strand1,strand2):
	#ensure both strands are equal length
	strand1 = strand1[:len(strand2)]
	strand2 = strand2[:len(strand1)]
	
	hamming_distance = 0
	#return sum of boolean tests for the equivalent letters in my strands
	#I love how clean this is!
	return sum(x != y for x, y in zip(strand1, strand2))
