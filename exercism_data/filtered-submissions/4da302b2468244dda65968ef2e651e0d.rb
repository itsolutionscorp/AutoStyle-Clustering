class Hamming
	def compute(s1,s2)
		c = 0
		(0..s1.length).each do |i|
			c += 1 unless s1[i] == s2[i]
		end
		c
	end
end
