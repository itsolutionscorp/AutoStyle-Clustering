def compute(strand1, strand2)

		iter_s1 = strand1.each_char
		iter_s2 = strand2.each_char

		pairs = iter_s1.zip(iter_s2)
		distance = pairs.map {|pair| (pair[0] == pair[1])? 0 : 1}.inject(0) {|acc, x| acc + x}











	end