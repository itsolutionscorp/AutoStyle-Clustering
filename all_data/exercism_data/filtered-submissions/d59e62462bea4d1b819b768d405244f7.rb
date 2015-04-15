def compute(first_dna, second_dna)
		(0..first_dna.size - 1).inject(0){ |count, index| count + (first_dna[index] != second_dna[index] ?  1 : 0)}
	end