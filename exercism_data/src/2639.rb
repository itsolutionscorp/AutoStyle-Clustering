def compute(strand_1, strand_2)
		arrStrand_1 = strand_1.scan(/\w/)
		arrStrand_2 = strand_2.scan(/\w/)
		hamming_distance = 0
		(0..arrStrand_1.length).each do |n|
			hamming_distance += 1 if arrStrand_1[n] != arrStrand_2[n]
		end
		return hamming_distance
	end