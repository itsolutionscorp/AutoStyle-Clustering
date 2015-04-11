class DNA
	def initialize(dna)
		@nucleotides = dna.split("")
	end

	def to_rna
		@nucleotides.inject("") { |rna, dna_nucleotide| rna + rna_nucleotide_of(dna_nucleotide)}
	end

	def rna_nucleotide_of(nucleotide)
		if nucleotide == "T"
			"U"
		else
			nucleotide	
		end
	end
end
