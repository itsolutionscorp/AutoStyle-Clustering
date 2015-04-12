class Hamming
	def compute(first_dna_strand, second_dna_strand)
		first_dna_strand, second_dna_strand = [first_dna_strand.chars, second_dna_strand.chars].sort_by { |x| x.length }
		first_dna_strand.count { |code_of_dna_nucleotide| code_of_dna_nucleotide != second_dna_strand.shift}
	end
end
