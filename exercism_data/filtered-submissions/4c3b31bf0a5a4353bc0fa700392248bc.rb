class Hamming
	def compute(str1, str2)
		len = [str1.length, str2.length].min
		distance = 0
		for i in 0...len
			if str1[i] != str2[i]
				distance += 1
			end
		end
		distance
	end
end
