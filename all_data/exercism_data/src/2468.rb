def compute(strand1, strand2)
		hamming_distance = 0
		short_strand = if strand1.length <= strand2.length then strand1 else strand2 end
		short_strand.length.times do |i|
			if strand1[i] != strand2[i]
				hamming_distance += 1
			end
		end
		hamming_distance
	end