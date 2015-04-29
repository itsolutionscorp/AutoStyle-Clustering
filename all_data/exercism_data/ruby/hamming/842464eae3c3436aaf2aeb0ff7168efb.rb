class Hamming
	def self.compute(first_dna, second_dna)
		num_of_diff = 0
		first_dna.each_char.with_index {|base, index| num_of_diff += 1 unless base == second_dna[index] }
		num_of_diff
	end
end
