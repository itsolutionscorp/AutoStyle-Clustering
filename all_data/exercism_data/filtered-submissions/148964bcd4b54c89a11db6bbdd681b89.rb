def compute(arg1,arg2)
	result = 0
		for i in 0..arg1.size
		result += 1	if arg1.split(//)[i] != arg2.split(//)[i]
		end
		result
	end