class Complement
	def self.of_dna(dna)
		dna_array = dna.split('')
		a = dna_array.map { |base| 
			if base.downcase == 'a'
				base = 'U'
			elsif base.downcase == 't'
				base = 'A'
			elsif base.downcase == 'c'
				base = 'G'
			else
				base = 'C'
			end
				 }
			a.join('')
	end

	def self.of_rna(rna)
		dna_array = rna.split('')
		a = dna_array.map { |base| 
			if base.downcase == 'a'
				base = 'T'
			elsif base.downcase == 'u'
				base = 'A'
			elsif base.downcase == 'c'
				base = 'G'
			else
				base = 'C'
			end
				 }
			a.join('')
	end
end
