class Hamming
	def compute(str1, str2)
		dist = 0
		str1.split("").each_with_index { |s, ind| dist += 1 if s != str2[ind] }
		dist
	end
end
