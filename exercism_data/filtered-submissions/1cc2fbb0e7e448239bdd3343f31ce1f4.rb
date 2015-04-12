class Hamming
	def compute(var1, var2)
		sz_a = var1.size
		sz_b = var2.size

		max = 0
		if sz_a == sz_b
			max = sz_a
		elsif sz_a > sz_b
			max = sz_b
		else
			max = sz_a
		end

		count = 0
		max = max - 1

		for i in 0..max do
			count+=1 if var1[i] != var2[i]
		end
		
		return count
	end
end
