def hamming(strand1, strand2):
	strandList = []
	hammingnum = 0
	#Determine which strand is longer
	if len(strand1) >= len(strand2):
		l = strand1
		s = strand2
	else:
		l = strand2
		s = strand1
	#Add random integer (in this case 0) to equalize lengths
	s += '0' * (len(l)-len(s))
	for item in range(len(l)):
		#If the two letters match, increase hamming difference
		if l[item] != s[item]:
			hammingnum += 1
	return hammingnum
