class Hamming
	def self.compute(str1, str2)
		cnt = 0
		[str1.size, str2.size].min.times do |i|
			cnt += 1 if str1[i] != str2[i]
		end
		cnt
	end
end
