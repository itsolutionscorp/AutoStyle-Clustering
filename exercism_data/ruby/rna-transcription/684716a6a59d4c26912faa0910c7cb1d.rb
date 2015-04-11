class Complement
	@complements = { 
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
    
	def self.of_dna(a)
		a.chars.map{ |i| @complements[i] }.join
	end

	def self.of_rna(a)
		a.chars.map{ |i| @complements.key(i) }.join
	end
end
