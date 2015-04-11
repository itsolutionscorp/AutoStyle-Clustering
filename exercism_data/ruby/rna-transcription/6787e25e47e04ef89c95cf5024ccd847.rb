class Complement

	def self.of_dna(string)
		string.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
	end

	def self.of_rna(string)
		string.gsub(/[GCAU]/, 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A')
	end

end
