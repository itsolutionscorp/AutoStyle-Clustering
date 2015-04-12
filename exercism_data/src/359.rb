def compute(strand1, strand2)
		hamming_sum = 0
		i = 0
		length = [strand1.length, strand2.length].min
		while i < length
			if strand1[i] != strand2[i]
				hamming_sum += 1
			end
			i += 1
		end
		hamming_sum
	end