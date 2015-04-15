def compute(first_dna_string, second_dna_string)
		diff, i = 0, 0
		first_dna_string.each_char do |l|
			diff += 1 if l != second_dna_string[i] and i < second_dna_string.length
			i += 1
		end
		diff
	end