class Hamming
	def self.compute(a, b)
		if a.length != b.length
			raise ArgumentError "Invalid strings"
		end
		c = 0
		(0..a.length - 1).each do |i|
			c += 1 if a[i] != b[i]
		end
		c
	end
	
end
