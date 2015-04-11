class Hamming

	def self.compute(str1, str2)
		i = 0
		min_len = [str1.length, str2.length].min
		for j in 0..(min_len-1)
			if str1[j] != str2[j]
				i = i + 1
			end
		end

		return i
	end

end
