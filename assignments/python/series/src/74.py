def slices(digits, length):
	outputlist = []
	if length == 0:
		raise ValueError('length 0 not supported')
	if len(str(digits)) < length:
		raise ValueError('Not enough digits for desired length')
	for n in range( 0 ,len(digits)):
		if n + length - 1 < len(digits):
			sublist = []
			for x in digits[n:n+length]:
				sublist.append(int(x))
			outputlist.append(sublist)
	return outputlist
