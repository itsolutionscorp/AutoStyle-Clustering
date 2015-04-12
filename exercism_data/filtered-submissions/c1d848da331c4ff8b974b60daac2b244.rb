class Hamming
	def compute(strand1, strand2)
		dna_comparison = strand1.split('').zip(strand2.split(''))
		dna_comparison.select{ |nucleotide1, nucleotide2| nucleotide1 != nucleotide2 }.count
	end
end
