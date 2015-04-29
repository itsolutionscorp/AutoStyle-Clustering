class Complement

	def self.of_dna(strand)
		dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		strand = strand.split("")
		strand.map! { |value| dna_to_rna[value] }
		strand.join("")
	end

	def self.of_rna(strand)
		rna_to_dna = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
		strand = strand.split("")
		strand.map! { |value| rna_to_dna[value] }
		strand.join("")
	end
end
