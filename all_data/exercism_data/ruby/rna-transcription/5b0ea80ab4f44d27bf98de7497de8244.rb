class Complement
	@dna_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	@rna_dna = @dna_rna.invert

	def self.of_dna(dna)
		complement(dna, @dna_rna)
	end

	def self.of_rna(rna)
		complement(rna, @rna_dna)
	end

	def self.complement(input, mappings)
		input.each_char.map { |b| 
			mappings[b]
		}.join
	end
end
