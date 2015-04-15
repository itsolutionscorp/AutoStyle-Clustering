def compute(string1, string2)
		strand_length = 0
		if string1.length <= string2.length
			strand_length = string1.length
		else
			strand_length = string2.length
		end
		hamming_count = 0
		(0...strand_length).each do |i|
			if string1[i] != string2[i]
				hamming_count += 1
			end
		end
		return hamming_count
	end