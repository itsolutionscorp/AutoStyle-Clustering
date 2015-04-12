class Hamming
	def compute(str1,str2)
		diff = 0
		shorter_length = [str1.length, str2.length].min

		for i in 0...shorter_length
			if str1[i] != str2[i]
				diff += 1
			end
		end

		return diff
	end
end
