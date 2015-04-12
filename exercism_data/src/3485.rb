def compute(string1, string2)
		seq1 = string1.chars.to_a
		seq2 = string2.chars.to_a
		hamming_distance = 0
		counter = 0
		if seq2.length < seq1.length
			seq2.each do |x|
				if x != seq1[counter]
					hamming_distance += 1
				end
					counter += 1
			end
		else
			seq1.each do |x|
				if x != seq2[counter]
					hamming_distance += 1
				end
					counter += 1
			end
		end
		return hamming_distance
	end