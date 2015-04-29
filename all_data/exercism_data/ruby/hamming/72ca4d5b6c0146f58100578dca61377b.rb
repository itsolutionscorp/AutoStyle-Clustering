class Hamming
	def self.get_distance(strand_1, strand_2)
		eq_strand_1, eq_strand_2 = equalize_length(strand_1, strand_2)
		count_mutations(eq_strand_1, eq_strand_2)
	end

	private

	def self.count_mutations(eq_strand_1, eq_strand_2)
		counter = 0

		eq_strand_1.split('').each_with_index do |nucleotide, i|
			counter += 1 unless is_same?(nucleotide, eq_strand_2[i])
		end

		counter
	end

	def self.equalize_length(strand_1, strand_2)
		minimum_length = find_minimum_length(strand_1, strand_2)

		return strand_1[0..minimum_length - 1], strand_2[0..minimum_length - 1]
	end

	def self.find_minimum_length(strand_1, strand_2)
		[strand_1.length, strand_2.length].min
	end

	def self.is_same?(nucleotide_1, nucleotide_2)
		nucleotide_1 == nucleotide_2
	end
end 
