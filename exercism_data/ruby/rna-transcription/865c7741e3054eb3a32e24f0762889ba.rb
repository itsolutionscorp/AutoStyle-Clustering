class Complement
	@@complements = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}

	def self.of_dna str
		str.chars.map{ |char|
			@@complements[char]
		}.join('')
	end

	def self.of_rna str
		str.chars.map{ |char|
			@@complements.key(char)
		}.join('')
	end

end
