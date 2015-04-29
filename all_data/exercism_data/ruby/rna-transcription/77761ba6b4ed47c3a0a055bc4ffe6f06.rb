class Complement
	@@dna_nucleotides = 'GCTA'
	@@rna_nucleotides = 'CGAU'

	def self.of_dna(dna)
		dna.tr(@@dna_nucleotides, @@rna_nucleotides)
	end

	def self.of_rna(rna)
		rna.tr(@@rna_nucleotides, @@dna_nucleotides)
	end
end
