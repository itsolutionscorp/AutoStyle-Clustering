def hamming(strand1,strand2):
	val = 0
	for n1,n2 in map(None,strand1,strand2):
		if n1 != n2 or not (n1 or n2):
			val += 1
	return val
