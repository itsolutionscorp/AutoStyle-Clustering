class Complement

	def self.of_dna(dna_string)
		rna_string = ""
		for i in 0..dna_string.length
			case dna_string[i]
			when "G"
				rna_string << "C"
			when "C"
				rna_string << "G"
			when "T"
				rna_string << "A"
			when "A"
				rna_string << "U"
			end
		end
		rna_string
	end
	
	def self.of_rna(rna_string)
		dna_string = ""
		for i in 0..rna_string.length
			case rna_string[i]
			when "C"
				dna_string << "G"
			when "G"
				dna_string << "C"
			when "A"
				dna_string << "T"
			when "U"
				dna_string << "A"
			end
		end
		dna_string
	end

end
