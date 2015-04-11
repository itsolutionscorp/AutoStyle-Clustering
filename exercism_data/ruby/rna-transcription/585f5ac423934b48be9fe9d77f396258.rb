class Complement
	def self.of_dna(dna)
		rna = ''
		dna.each_char { |x|
			if x == 'G'
				x = 'C'
			elsif x == 'C'
				x = 'G'
			elsif x == 'T'
				x = 'A'
			elsif x == 'A'
				x = 'U'
			end
			rna << x
		}
		rna
	end

	def self.of_rna(rna)
		dna = ''
		rna.each_char { |x|
			if x == 'C'
				x = 'G'
			elsif x == 'G'
				x = 'C'
			elsif x == 'A'
				x = 'T'
			elsif x == 'U'
				x = 'A'
			end
			dna << x
		}
		dna
	end
end
