class Complement

	def self.of_dna(input)
		input.gsub(/[GCAT]/, 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A') 
	end 
     
	def self.of_rna(input)
		input.gsub(/[GCUA]/, 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T') 
	end 

end
