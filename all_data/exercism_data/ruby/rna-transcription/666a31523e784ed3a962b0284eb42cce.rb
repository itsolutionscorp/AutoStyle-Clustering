class DNA
	def initialize(dna)
		@dna = dna.split("")
	end

	def to_rna
		rna = ""
		@dna.each do |nucleotide|
			if nucleotide =='T'
				rna +='U'
			else
				rna += nucleotide
			end
		end
		return rna
	end
end
