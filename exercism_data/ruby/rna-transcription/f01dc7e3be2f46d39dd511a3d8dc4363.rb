class Complement

	@@dna_complements = Hash["G" => "C", "C" => "G", "T" => "A", "A" => "U"]
	@@rna_complements = @@dna_complements.invert

	def Complement.of_dna(dna)
		dna = dna.split(//)
		rna = ""
		for nucleotide in dna
			rna += @@dna_complements[nucleotide]
		end
	
	rna	
	end

	def Complement.of_rna(rna)
		rna = rna.split(//)
		dna = ""
		for nucleotide in rna
			dna += @@rna_complements[nucleotide]
		end
	
	dna	
	end

end
