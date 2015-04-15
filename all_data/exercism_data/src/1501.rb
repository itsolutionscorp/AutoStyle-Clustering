def compute(dna1,dna2)
		hamming_difference = 0
		if dna1.size <= dna2.size
			dna1.split("").each_index do |index|
				if dna1[index].ord != dna2[index].ord
					hamming_difference = hamming_difference + 1
				end
			end
		else
			dna2.split("").each_index do |index|
				if dna1[index].ord != dna2[index].ord
					hamming_difference = hamming_difference + 1
				end
			end
		end
   return hamming_difference
	end