class Complement
	def self.of_dna(dna)
		i = dna.length
		x = 0
		while (x != i)
			case dna[x]
			when 'G'
				dna[x] = 'C'
			when 'C'
				dna[x] = 'G'
			when 'T'
				dna[x] = 'A'
			when 'A'
				dna[x] = 'U'
			end
			x += 1
		end
		return dna
	end
	def self.of_rna(rna)
		i = rna.length
		x = 0
		while (x != i)
			case rna[x]
			when 'C'
				rna[x] = 'G'
			when 'G'
				rna[x] = 'C'
			when 'A'
				rna[x] = 'T'
			when 'U'
				rna[x] = 'A'
			end
			x += 1
		end
		return rna
	end
end
