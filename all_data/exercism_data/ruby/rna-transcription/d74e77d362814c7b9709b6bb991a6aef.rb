class DNA
	def initialize(dna)
		@nucleotides =dna.split("")
	end

	def to_rna
		rna= ""
		@nucleotides.each do |nucleotide| 
			if nucleotide == "T"
				rna= rna + "U"
			else
				rna= rna + nucleotide
			end
		end
		rna
	end
end	
