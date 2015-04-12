class Hamming
	def compute(var1, var2)
		sz_a = var1.size
		sz_b = var2.size

		min = [var1.size, var2.size].min()

		count = 0
		min = min - 1

		for i in 0..min do
			count+=1 if var1[i] != var2[i]
		end
		
		return count
	end
end
