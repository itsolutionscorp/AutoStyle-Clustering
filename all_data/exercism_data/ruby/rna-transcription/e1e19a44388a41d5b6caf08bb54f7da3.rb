class Complement

	def self.of_dna(dna_strand)
		
		rna = ""

		dna_strand.chars.each do |dna|

			case dna
				when dna = "G"
					rna += "C"

				when dna = "C"
					rna += "G"

				when dna = "T"
					rna += "A"

				when dna = "A"
					rna += "U"

			end
		end

		return rna
	end

	def self.of_rna(rna_strand)
		
		dna = ""

		rna_strand.chars.each do |rna|

			case rna
				when rna = "G"
					dna += "C"

				when rna = "C"
					dna += "G"

				when rna = "U"
					dna += "A"

				when rna = "A"
					dna += "T"
					
			end
		end

		return dna
	end

end
