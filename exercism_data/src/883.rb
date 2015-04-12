def compute(dna_strand1, dna_strand2)
		return 0 if dna_strand1 == dna_strand2
		dna_strand2 = dna_strand2.chars
		dna_strand1.chars.select { |nucleotide| nucleotide != dna_strand2.next }.count
	end