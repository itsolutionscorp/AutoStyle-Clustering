class Hamming

	def self.compute(string_one, string_two)
		if (string_one.length > string_two.length)
			return array_compare(string_one[0..string_two.length-1], string_two)
		else
			return array_compare(string_one, string_two[0..string_one.length-1])
		end
	end

	def self.array_compare(a, b)
		count = 0
		for i in 0..a.length-1
			if a[i] != b[i] 
				count += 1
			end
		end
		return count
	end
	
end
