def compute(v1,v2)
		h = 0
		for pos in 0..v1.length-1
			if v1[pos]!=v2[pos]
			h=h+1
			end
		end
		return h
	end