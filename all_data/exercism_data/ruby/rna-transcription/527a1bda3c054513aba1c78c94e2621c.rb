class Complement
	def self.of_dna str
		complements = {
			'G' => 'C',
			'C' => 'G',
			'T' => 'A',
			'A' => 'U'
		}
		Complement::get_complement complements,str
	end
	def self.of_rna str
		complements = {
			'G' => 'C',
			'C' => 'G',
			'A' => 'T',
			'U' => 'A'
		}
		Complement::get_complement complements,str
	end
	def self.get_complement complements,str
		str.split('').map{|char| complements[char]}.join('')
	end
end
