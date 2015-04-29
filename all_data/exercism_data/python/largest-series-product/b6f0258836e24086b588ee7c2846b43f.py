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

def largest_product(digits, length):
	if length == 0:
		return 1
	slcs = slices(digits,length)
	largest = 1
	for slc in slcs:
		product = 1
		for x in slc:
			product *= x
		largest = max(largest, product)

	return largest
