class Complement
	class << self
		def of_dna(dna)
			dna = dna.split("")
			rna = []

			for nucleotide in dna
				case nucleotide
					when 'G'
						rna.push('C')
					when 'C'
						rna.push('G')
					when 'T'
						rna.push('A')
					when 'A'
						rna.push('U')
				end
			end

			rna = rna.join("")

			rna

		end

		def of_rna(rna)
			rna = rna.split("")
			dna = []

			for nucleotide in rna
				case nucleotide
					when 'C'
						dna.push('G')
					when 'G'
						dna.push('C')
					when 'A'
						dna.push('T')
					when 'U'
						dna.push('A')
				end
			end

			dna = dna.join("")

			dna

		end

	end
end
