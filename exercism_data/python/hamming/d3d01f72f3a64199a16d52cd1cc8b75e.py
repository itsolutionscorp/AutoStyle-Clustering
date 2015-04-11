def hamming(hcode1, hcode2):

	hamming = 0	

	# compare each position up to the length of the shorter string,
	# increase counter for each mismatch
	for a in range(min(len(hcode1),len(hcode2))):
		if hcode1[a] != hcode2[a]:
			hamming += 1
			
	# return counter + difference in string length		
	return hamming + abs(len(hcode1) - len(hcode2))
