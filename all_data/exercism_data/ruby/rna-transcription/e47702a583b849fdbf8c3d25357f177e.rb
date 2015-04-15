class Complement
	def self.of_dna(dna)
		rna = String.new
		0.upto(dna.size) do |nucl|
			case dna[nucl]
			when 'G'
				rna += 'C'
			when 'C'
				rna += 'G'
			when 'T'
				rna += 'A'
			when 'A'
				rna += 'U'
			end
		end
		rna
	end


	def self.of_rna(rna)
		dna = String.new
		0.upto(rna.size) do |nucl|
			case rna[nucl]
			when 'C'
				dna += 'G'
			when 'G'
				dna += "C"
			when 'A'
				dna += 'T'
			when 'U'
				dna += 'A'
			end
		end
		dna
	end
end
