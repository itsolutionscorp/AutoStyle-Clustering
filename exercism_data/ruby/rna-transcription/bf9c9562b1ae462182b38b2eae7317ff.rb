class Complement
	def self.of_dna(dna_string)
		dna_array = dna_string.split('')
		rna_array = []
		dna_array.each do |nucleotide|
			if nucleotide == 'A'
				rna_array.push('U')
			elsif nucleotide == 'G'
				rna_array.push('C')
			elsif nucleotide == 'C'
				rna_array.push('G')
			else nucleotide == 'T'
				rna_array.push('A')
			end
		end
		return rna_array.join('')
	end

	def self.of_rna(rna_string)
		rna_array = rna_string.split('')
		dna_array = []
		rna_array.each do |nucleotide|
			if nucleotide == 'U'
				dna_array.push('A')
			elsif nucleotide == 'C'
				dna_array.push('G')
			elsif nucleotide == 'G'
				dna_array.push('C')
			else nucleotide == 'A'
				dna_array.push('T')
			end
		end
		return dna_array.join('')
	end
end
