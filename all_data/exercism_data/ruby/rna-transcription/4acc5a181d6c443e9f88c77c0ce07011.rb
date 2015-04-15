class DNA
	def initialize(dna)
		@nucleotides = dna.split("")
	end

	def to_rna
		@nucleotides.inject("") do |dna, nucleotides|
			dna += rna_nucleotides(nucleotides)
		end
	end

	def rna_nucleotides(nucleotide)
		if nucleotide == 'T'
			"U"
		else
			nucleotide
		end
	end
end
