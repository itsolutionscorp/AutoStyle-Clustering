class Complement
	def self.of_dna(dna)
		rna = ""
		dna = dna.split("")
		# dnanuc means "dna nucleotide"
		dna.each do |dnanuc|
			if dnanuc == "G"
				rna << "C"
			elsif dnanuc == "C"
				rna << "G"
			elsif dnanuc == "T"
				rna << "A"
			elsif dnanuc == "A"
				rna << "U"
			else
				raise "Not a real DNA strand"
			end
		end
		rna
	end
	
	def self.of_rna(rna)
		dna = ""
		rna = rna.split("")
		# rnanuc means "rna nucleotide"
		rna.each do |rnanuc|
			if rnanuc == "C"
				dna << "G"
			elsif rnanuc == "G"
				dna << "C"
			elsif rnanuc == "A"
				dna << "T"
			elsif rnanuc == "U"
				dna << "A"
			else
				raise "Not a real RNA strand"
			end
		end
		dna
	end
end
