class Complement
	def self.of_dna(dna)
		dna.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
	end
end
