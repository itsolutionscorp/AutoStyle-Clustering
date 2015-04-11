"""
Had to figure out how to find the shortest string so that the for loop
wouldn't take the string index out of range. Counted strand differences
along the smallest comparable lengths, then tacked on difference between
min and lax strand length in order to satisfy the test

"""


def hamming(s1, s2):
	ham = 0
	s3 = [s1,s2]
	maxl = max(s3, key=len)
	minl = min(s3, key=len)

	for x in range(0,len(minl)):
		
		if s1[x] != s2[x]:
			ham  += 1
	
	return ham + (len(maxl) - len(minl))
