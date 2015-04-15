def compute(strand_1, strand_2)
		hamming_distance = 0
		(0..strand_1.length).each do |n|
			hamming_distance += 1 if strand_1[n] != strand_2[n]
		end
		return hamming_distance
	end