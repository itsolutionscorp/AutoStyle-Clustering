def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count { |pair| !(pair[0] == pair[1]) }
	end