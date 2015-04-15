class DNA	
	def initialize(base)
        @base = base.split("")
	end

	def to_rna
		@base.inject("") do |dna, nucleotide|
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
