class Hamming
	def compute(a, b) 
		a = a.split(//)
		b = b.split(//)
		hamming_number = 0
		for i in 0..a.length do
			if a[i] == b[i]
			else
				hamming_number += 1
			end
		end
		hamming_number
	end
end
