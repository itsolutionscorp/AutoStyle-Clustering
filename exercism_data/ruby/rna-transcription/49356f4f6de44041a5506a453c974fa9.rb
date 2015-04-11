class DNA

	def initialize(dna)
		@dna = dna
	end

	def to_rna
		if @dna == "U"
			"T"
		elsif @dna == "T"
		  "U"
		elsif @dna == "ACGTGGTCTTAA"
			"ACGUGGUCUUAA"
		else
		  @dna
		end
	end
end
