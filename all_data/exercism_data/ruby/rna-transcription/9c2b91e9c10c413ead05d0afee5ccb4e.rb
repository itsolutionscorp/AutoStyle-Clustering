class Complement

	def self.of_dna(dna_strand)
		dna_strand = dna_strand.split("")
		rna_letters = []

		dna_strand.each do |letter|
			case letter
			when "G"
				rna_letters << "C"
			when "C"
				rna_letters << "G"
			when "T"
				rna_letters << "A"
			when "U"
				rna_letters << "A"
			else
				rna_letters << "U"
			end
		end
		rna_strand=rna_letters.join("")
		return rna_strand
	end

	def self.of_rna(rna_strand)
		rna_strand = rna_strand.split("")
		dna_letters = []

		rna_strand.each do |letter|
			case letter
			when "G"
				dna_letters << "C"
			when "C"
				dna_letters << "G"
			when "T"
				dna_letters << "A"
			when "U"
				dna_letters << "A"
			else
				dna_letters << "T"
			end
		end
		dna_strand=dna_letters.join("")
		return dna_strand
	end


end
