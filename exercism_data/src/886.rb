def compute(strand_one, strand_two)
		hamming_distance = 0

		for i in 0..strand_one.length
			if strand_one[i] != strand_two[i]
				hamming_distance += 1
			end
		end

		hamming_distance
	end