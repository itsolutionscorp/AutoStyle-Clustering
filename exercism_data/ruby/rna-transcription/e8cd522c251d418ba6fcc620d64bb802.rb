class Complement
	
	COMPLEMENTS = {  'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'  }
	
	def self.of_dna(strand)
		strand.chars.collect { |x| x = COMPLEMENTS[x] }.join
	end

	def self.of_rna(strand)
		strand.chars.collect { |x| x = COMPLEMENTS.key(x) }.join
	end
	

end
