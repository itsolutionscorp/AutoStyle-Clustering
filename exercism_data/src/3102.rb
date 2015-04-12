def compute(strand1, strand2)
		distance = 0
		strand1.each_char.with_index{|c, i|
			distance+=1 if strand2[i] && strand2[i] != c
		}
		return distance
	end