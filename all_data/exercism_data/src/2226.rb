def compute(strand1, strand2)
		min_length = [strand1.size, strand2.size].min
		(0...min_length).count { |i| strand1[i] != strand2[i] }
	end