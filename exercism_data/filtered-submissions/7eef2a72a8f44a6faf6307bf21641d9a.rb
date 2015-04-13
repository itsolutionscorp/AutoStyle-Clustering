def compute(str1,str2)
		iter = str1.length()<str2.length() ? str1.length() : str2.length()
		diff = 0
		iter.times do |i|
			diff += str1[i] != str2[i] ? 1 : 0
			end
		return diff
	end