def compute(arg1,arg2)
		sum=0
		i=0

		while i < arg1.length
			if arg1[i] != arg2[i]
				sum = sum + 1
			end
			i = i + 1
		end


		return sum
	end