def compute(var1, var2)

		min = [var1.size, var2.size].min()

		count = 0

		for i in 0..min-1 do
			count+=1 if var1[i] != var2[i]
		end

		return count
	end