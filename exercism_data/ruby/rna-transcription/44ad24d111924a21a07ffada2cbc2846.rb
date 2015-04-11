class Complement
	def self.of_dna(dna)
		rna = ""
		dna.each_char do |nucleotide|
			if nucleotide == "A"
				rna << "U"
			else
				rna << CGUT_complements(nucleotide)
			end
		end
		rna
	end

	def self.of_rna(rna)
		dna = ""
		rna.each_char do |nucleotide|
			if nucleotide == "A"
				dna << "T"
			else
				dna << CGUT_complements(nucleotide)
			end
		end
		dna
	end

	def self.CGUT_complements(nucleotide)
		case 
		when nucleotide == "C"
			"G"
		when nucleotide == "G"
			"C"
		when nucleotide == "U" || nucleotide == "T"
			"A"
		end
	end
end
