module Complement	
	def self.of_dna(strands)
		strands.gsub(/[GCAT]/, 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A')
	end

	def self.of_rna(strands)
		strands.gsub(/[GCAU]/, 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A')
	end
end
