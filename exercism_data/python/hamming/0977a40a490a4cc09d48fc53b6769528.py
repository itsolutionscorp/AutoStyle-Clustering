def hamming(hcode1, hcode2):
	# figure out which string is the longest
	if len(hcode1) >= len(hcode2):
		nmax = len(hcode1)
		nmin = len(hcode2)
	else:
		nmax = len(hcode2)
		nmin = len(hcode1)
	
	hamming = 0	
	# compare each position, increase counter for each mismatch
	for a in range(0,nmin):
		if hcode1[a] != hcode2[a]:
			hamming += 1
			
	# return counter + difference in string length		
	return hamming + nmax - nmin
