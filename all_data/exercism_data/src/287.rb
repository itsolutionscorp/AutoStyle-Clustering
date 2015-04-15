def compute(strand_a, strand_b)
		diff = 0
		[strand_a.size, strand_b.size].min.times { | idx | diff += 1 if strand_a[idx] != strand_b[idx] }
		diff
	end