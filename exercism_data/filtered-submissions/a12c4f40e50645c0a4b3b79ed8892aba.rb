def compute(s1, s2)
		s1.chars.zip(s2.chars).select{ |a| a[0] && a[1] && a[0] != a[1]}.length
	end