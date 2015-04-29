class Hamming
	def self.compute(s1, s2)
		result = 0
		(s1.length < s2.length ? s1.length : s2.length).times do |i|
			result += 1 unless s1[i] == s2[i]
		end
		result
	end
end
