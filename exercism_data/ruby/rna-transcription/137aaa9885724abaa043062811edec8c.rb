class Complement
	def self.of_dna(dna)
		rna = ""
		dna.each_char do |nucleotide|
			case 
			when nucleotide == "C"
				rna << "G"
			when nucleotide == "G"
				rna << "C"
			when nucleotide == "T"
				rna << "A"
			when nucleotide == "A"
				rna << "U"
			end
		end
		rna
	end

	def self.of_rna(rna)
		dna = ""
		rna.each_char do |nucleotide|
			case 
			when nucleotide == "C"
				dna << "G"
			when nucleotide == "G"
				dna << "C"
			when nucleotide == "U"
				dna << "A"
			when nucleotide == "A"
				dna << "T"
			end
		end
		dna
	end
end
