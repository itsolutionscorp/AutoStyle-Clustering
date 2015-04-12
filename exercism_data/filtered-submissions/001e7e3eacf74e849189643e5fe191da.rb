def compute(v1,v2)
		h = 0
		for pos in 0..v1.length-1
			v1[pos]!=v2[pos] ? h+=1 : nil
		end
		h
	end