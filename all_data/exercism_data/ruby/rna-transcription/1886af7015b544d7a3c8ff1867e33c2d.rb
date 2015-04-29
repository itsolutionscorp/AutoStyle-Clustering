class DNA

	def initialize(dna)
		@n=dna.split("")
		
	end

	def to_rna
		@n.inject("") do| dna, one_n |
			dna += rna_n(one_n)
		end
	end

	def rna_n(one_n)
		if one_n == "T"
			"U"
		else
			one_n
		end
	end
end
