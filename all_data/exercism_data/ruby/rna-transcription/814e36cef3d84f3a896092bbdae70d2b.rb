class Complement
	# DNA to RNA complements:
	# * `G` -> `C`
	# * `C` -> `G`
	# * `T` -> `A`
	# * `A` -> `U`

	def self.of_dna(rna_str)
		of_acid(rna_str, "DNA")
	end
	
	def self.of_rna(dna_str)
		of_acid(dna_str, "RNA")
	end
	
	def self.of_acid(str, acid)
		initial_nucleotides = str.chars
		complement_nucleotides = []
		
		initial_nucleotides.each { |n|
			complement_nucleotides.push(acid_conversion(n, acid))
		}
		
		return complement_nucleotides.join
	end
	
	def self.acid_conversion(nucleotide, acid)
		dna_rna_complements = {G: 'C', C: 'G', T: 'A', A: 'U'} 
		rna_dna_complements = {C: 'G', G: 'C', A: 'T', U: 'A'}
		return acid == "DNA" ? dna_rna_complements[nucleotide.to_sym] : rna_dna_complements[nucleotide.to_sym]
	end
	
end
