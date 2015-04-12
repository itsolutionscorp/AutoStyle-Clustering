class Hamming

	def compute(string_one, string_two)
		count = 0
		for i in 0..([string_one.length, string_two.length].min-1)
			if string_one[i] != string_two[i] 
				count += 1
			end
		end
		return count
	end
	
end
