def compute(strand1, strand2)

		iter_s1 = strand1.each_char
		iter_s2 = strand2.each_char

		iter_s1.zip(iter_s2).count {|n1, n2| n1 != n2}
	end