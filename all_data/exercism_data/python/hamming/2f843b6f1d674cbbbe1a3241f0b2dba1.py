def hamming(dnaStr, checkDnaStr):
	'''Take a DNA string as input, return the hamming distance.'''
	hamNum = 0
	index = 0

	if len(dnaStr) < len(checkDnaStr) : 
		tmp = dnaStr
		dnaStr = checkDnaStr
		checkDnaStr = tmp

	for nucleotide in dnaStr :
		if index >= len(checkDnaStr) or nucleotide != checkDnaStr[index]: hamNum += 1
		index += 1

	return hamNum
