class Complement
	def self.of_dna(dna)
		rna = ''
		dna.split("").each do |char|
			case char
				when 'G'
					rna += 'C'				
				when 'C'
					rna += 'G'				
				when 'A'
					rna += 'U'				
				when 'T'
					rna += 'A'				
				end
		end
		return rna
	end

	def self.of_rna(rna)
		dna = ''
		rna.split("").each do |char|
			case char
				when 'C'
					dna += 'G'				
				when 'G'
					dna += 'C'				
				when 'U'
					dna += 'A'				
				when 'A'
					dna += 'T'				
				end
		end
		return dna
	end

end
