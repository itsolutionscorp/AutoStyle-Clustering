class Complement
	def self.of_dna(dna)
		for i in 0...dna.length
			case dna[i]
			when 'G'
				dna[i] = 'C'
			when 'C'
				dna[i] = 'G'
			when 'T'
				dna[i] = 'A'
			when 'A'
				dna[i] = 'U'
			end					
		end
		dna
	end

	def self.of_rna(rna)
		for i in 0...rna.length
			case rna[i]
			when 'C'
				rna[i] = 'G'
			when 'G'
				rna[i] = 'C'
			when 'A'
				rna[i] = 'T'
			when 'U'
				rna[i] = 'A'
			end
		end
		rna
	end
end
