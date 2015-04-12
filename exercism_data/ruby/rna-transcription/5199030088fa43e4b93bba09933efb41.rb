class Complement
	
	@complements = {  'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'  }
	
	def self.of_dna(strand)
		strand.chars.collect { |x| x = @complements[x] }.join
	end

	def self.of_rna(strand)
		strand.chars.collect { |x| x = @complements.key(x) }.join
	end
	

end