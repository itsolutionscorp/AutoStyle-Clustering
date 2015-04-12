class Hamming
	def compute(str1, str2)
		count = 0
		str1.chars.zip(str2.chars).each do |s1, s2|
			count += 1 if s1 != s2
		end
		count
	end
end
