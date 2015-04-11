class Complement
	def self.of_dna(rna)
		rna.gsub!(/[GCTA]/,'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
	end

	def self.of_rna(dna)
		dna.gsub!(/[GCAU]/,'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T')
	end
end
