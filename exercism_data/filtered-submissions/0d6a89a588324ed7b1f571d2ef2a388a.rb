module Hamming
	extend self

	def compute(dna_1_string, dna_2_string)
		size = [dna_1_string.length, dna_2_string.length].min
		dna_2_string.split(//).take(size).zip(dna_1_string.split(//).take(size)).reject { |pair| pair[0] == pair[1] }.size
	end

end
