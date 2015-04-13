def compute(first_dna, second_dna)
		count = 0
		0.upto(first_dna.size) { |i| count += 1 if first_dna[i] != second_dna[i] }

		return count
	end