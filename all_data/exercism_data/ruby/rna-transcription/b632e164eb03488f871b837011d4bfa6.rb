class Complement
	def self.of_dna(dna)
		return dna.tr("GCTA", "CGAU")
	end
	def self.of_rna(dna)
		return dna.tr("CGAU", "GCTA")
	end
end
