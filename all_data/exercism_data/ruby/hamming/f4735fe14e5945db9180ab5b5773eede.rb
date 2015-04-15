module Hamming

	def self.compute(a, b)
		if a.length < b.length
			max = a.length
		else 
			max = b.length
		end
		distance = 0
		for i in 0..max-1
			if a[i] != b[i]
				distance += 1
			end
		end
		return distance
	end
	
end
