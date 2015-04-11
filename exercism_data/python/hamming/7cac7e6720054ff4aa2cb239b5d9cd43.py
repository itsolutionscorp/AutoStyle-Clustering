def hamming(s1, s2):
	print zip(s1, s2)
    	return sum(ch1 != ch2 for ch1, ch2 in zip(s1, s2))
