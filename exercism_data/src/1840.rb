def compute(strand1, strand2)
		strand1 = strand1.split(//)
		strand2 = strand2.split(//)
		max = ( strand1.count < strand2.count ) ? strand1.count : strand2.count
		strand1 = strand1[0,max]
		strand2 = strand2[0,max]
		y = strand1.zip(strand2)
		y.inject(0) {|ret, h| ret += 1 if h.first != h.last; ret}
	end