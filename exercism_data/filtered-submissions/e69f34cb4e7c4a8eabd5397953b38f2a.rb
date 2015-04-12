def compute dna_a, dna_b
		hamming_val = 0

		dna_a.each_char.to_a.each_with_index {|base, index| 
			hamming_val += 1 if base != dna_b[index] 
		} 
		hamming_val
	end