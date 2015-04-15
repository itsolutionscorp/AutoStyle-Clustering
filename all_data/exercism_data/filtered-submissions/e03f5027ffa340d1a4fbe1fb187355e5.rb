def compute(s1, s2)
		s1.chars.zip(s2.chars).count{ |c1, c2| c1 && c2 && c1 != c2}
	end