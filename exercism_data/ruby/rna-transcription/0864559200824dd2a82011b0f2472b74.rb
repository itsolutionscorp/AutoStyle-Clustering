class DNA
	def initialize(dna)
		@nucleotides = dna.split("")
	end

	def to_rna
		@nucleotides.inject("") do |dna, nucleotide|
			dna += rna_nucleotides(nucleotide)
		end
	end

	def rna_nucleotides(nucleotide)
		if nucleotide == "T"
			"U"
		else
			nucleotide
		end
	end
end
