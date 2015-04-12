def compute(strand1, strand2)
		dna_comparison = strand1.chars.zip(strand2.chars)
		dna_comparison.count{ |nucleotide1, nucleotide2| nucleotide1 != nucleotide2 }
	end