class Complement
	def self.of_dna(dna)
		x = dna.chars.to_a
		@rna = []
		x.each do |x| 
			if x == 'C'
				@rna.push('G')
			elsif x == 'G'
				@rna.push('C')
			elsif x == 'T'
				@rna.push('A')
			elsif x == 'A'
				@rna.push('U')
			end
		end
		return @rna.join
	end
	def self.of_rna(rna)
		x = rna.chars.to_a
		@dna = []
		x.each do |x| 
			if x == 'C'
				@dna.push('G')
			elsif x == 'G'
				@dna.push('C')
			elsif x == 'U'
				@dna.push('A')
			elsif x == 'A'
				@dna.push('T')
			end
		end
		return @dna.join
	end
end
