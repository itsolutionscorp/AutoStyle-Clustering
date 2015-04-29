def slices(string, length):
	if length > len(string):
		raise ValueError
	listOfSlices = []
	for i in range(0,len(string)-length+1):
		aSlice = []
		for j in range(0, length):
			aSlice.append(int(string[i+j]))
		listOfSlices.append(aSlice)
	return listOfSlices

def largest_product(string, length):
	options = slices(string, length)
	maxProduct = 0
	for aList in options:
		product = 1
		for item in aList:
			product *= item
		if product > maxProduct:
			maxProduct = product
	return maxProduct
