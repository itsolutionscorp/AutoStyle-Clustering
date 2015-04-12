def compute(dna_strand1, dna_strand2)
		dna_strand2 = dna_strand2.each_char
		dna_strand1.each_char.count do |nucleotide|
			nucleotide != dna_strand2.next
		end
	end