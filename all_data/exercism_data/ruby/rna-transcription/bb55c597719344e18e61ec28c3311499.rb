class Complement

	def self.of_dna(dna)
		dna_seq = "GCTA"
		rna_seq = "CGAU"
		dna.tr(dna_seq, rna_seq)
	end

	def self.of_rna(rna)
		dna_seq = "GCTA"
		rna_seq = "CGAU"
		rna.tr(rna_seq, dna_seq)
	end
end
