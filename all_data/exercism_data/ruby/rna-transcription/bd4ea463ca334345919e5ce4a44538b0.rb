class Complement

	def self.of_dna(dna)
		i = 0
		while dna[i] do
			if dna[i] == 'G'
				dna[i] = 'C'
			elsif dna[i] == 'C'
				dna[i] = 'G'
			elsif dna[i] == 'T'
				dna[i] = 'A'
			elsif dna[i] == 'A'
				dna[i] = 'U'
			end
			i += 1
		end
		return dna
	end

	def self.of_rna(rna)
		i = 0
		while rna[i] do
			if rna[i] == 'C'
				rna[i] = 'G'
			elsif rna[i] == 'G'
				rna[i] = 'C'
			elsif rna[i] == 'U'
				rna[i] = 'A'
			elsif rna[i] == 'A'
				rna[i] = 'T'
			end
			i += 1
		end
		return rna
	end

end
