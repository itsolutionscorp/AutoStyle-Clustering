def hamming(strand1, strand2):
	out = abs(len(strand1)-len(strand2))
	for i in xrange(min(len(strand1),len(strand2))):
		if strand1[i]!=strand2[i]:
			out += 1
	return out
