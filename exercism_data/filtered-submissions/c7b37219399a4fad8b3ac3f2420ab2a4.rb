module Hamming
	
	def compute(str1, str2)
		str1.length < str2.length ? count = str1.length : count = str2.length
		diff = 0
		count.times {|i| diff += 1 if str1[i] != str2[i] }
		diff
	end
end
