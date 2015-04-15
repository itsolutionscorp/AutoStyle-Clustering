class Complement
	def initialize(string)
		@string = string
	end 

	def self.of_dna
		string.gsub(/[GCAT]/, 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A') 
	end 
     
	def self.of_rna
		string.gsub(/[GCAT]/, 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T') 
	end 

end
