class DNA
	def initialize(dna)
		@bases = dna.split("")
	end

	def to_rna
		@bases.inject("") do |dna, base|
			dna += rna_nucleotides(base)
		end
	end 

	def rna_nucleotides(base)
		if base == "T"
			"U"
		else 
			base
		end
	end
end 

#		if @nucleotide == "C"
#			return "C"
#		elsif @dna == "G"
#			return "G"
#		elsif @dna == "A"
#			return "A"
#		elsif @dna == "T"
#			return "U"
#		end

#bases = {
#	"C" => "C",
#	"G" => "G",
#	"A" => "A",
#	"T" => "U"
#}
