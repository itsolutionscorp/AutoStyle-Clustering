class Complement
	RNA_STRAND = "CGAU"
	DNA_STRAND = "GCTA"
	
	def self.of_dna(str)
		str.tr(DNA_STRAND, RNA_STRAND)
	end

	def self.of_rna(str)
		str.tr(RNA_STRAND, DNA_STRAND)
	end

end
