class Complement

	@@dna = "GCTA"
	@@rna = "CGAU" 

	def self.dna
		@@dna
	end

	def self.rna
		@@rna
	end

	def self.of_dna (dna_strand)
		return dna_strand.tr(self.dna, self.rna)
	end

	def self.of_rna (rna_strand)
		return rna_strand.tr(self.rna, self.dna)
	end

end
