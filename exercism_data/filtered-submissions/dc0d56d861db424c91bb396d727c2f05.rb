def compute (dna1, dna2)
		count = 0


		dna1.chars.each_index do |index|
			count += 1 if dna1[index] != dna2[index]
		end


		return count
	end