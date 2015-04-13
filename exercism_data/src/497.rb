def compute(strandA,strandB)
		if strandA.length != strandB.length
		end
	compares = 0

	for i in 0..strandA.length
		if strandA.getbyte(i) != strandB.getbyte(i) then
			compares+=1
			end
		end
		return compares
    end