class Complement
  DNA_STANDAR  = "GCTA"
  RNA_STAANDAR = "CGAU"

	def self.of_dna(dna)
		dna.tr(DNA_STANDAR, RNA_STAANDAR)
	end

	def self.of_rna(rna)
		rna.tr(RNA_STAANDAR,DNA_STANDAR)
	end

end
