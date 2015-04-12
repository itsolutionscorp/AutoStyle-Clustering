def compute(strand1, strand2)
		hamming = 0
		i = 0
	while i < strand1.length && i < strand2.length
		if strand1[i] != strand2[i]
			hamming += 1
		end
		i += 1
	end
	return hamming
	end