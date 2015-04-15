class Complement
	def self.of_dna(dna)
		dna.gsub(/[GCTA]/,'G' => 'C','C' => 'G','T' => 'A','A' => 'U')
	end

	def self.of_rna(rna)
		rna.gsub(/[CGAU]/,'G' => 'C','C' => 'G','A' => 'T','U' => 'A')
	end
end
