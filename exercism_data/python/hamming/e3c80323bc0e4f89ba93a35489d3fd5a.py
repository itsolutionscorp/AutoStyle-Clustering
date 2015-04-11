def hamming(a, b):

	if len(a) == len(b):
		length = len(a)
		count = 0
	elif len(a) > len(b):
		length = len (b)
		count = len(a) - len(b)
	elif len(a) < len(b):
		length = len(a)
		count = len(b) - len(a)

	for i in range(0, length):

		if not (a[i] == b[i]):
			count = count + 1		
	return count
