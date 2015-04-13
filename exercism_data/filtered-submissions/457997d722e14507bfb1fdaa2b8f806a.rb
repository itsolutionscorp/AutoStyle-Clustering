def compute(strand1, strand2)
		difference = 0
		i = 0
		while i < strand1.length && i < strand2.length
			if strand1[i] != strand2[i]
				difference += 1
			end
			i += 1
		end
	return difference
	end