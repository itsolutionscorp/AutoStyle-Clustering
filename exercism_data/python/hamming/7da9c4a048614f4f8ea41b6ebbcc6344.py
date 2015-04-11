
def hamming(strand1,strand2):
	equal_length_count = [x != y for (x, y) in zip(strand1, strand2)].count(True)

	if len(strand1) == len(strand2): 
		return equal_length_count
	else:
		difference = abs(len(strand1)-len(strand2))
		return equal_length_count + difference
