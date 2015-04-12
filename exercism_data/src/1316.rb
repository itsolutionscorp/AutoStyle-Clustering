class Hamming
	def compute(str1, str2)
		str1.upcase!
		str2.upcase!
		len = [str1.length, str2.length].min
		answer = 0
		for i in 0...len
			if str1[i] != str2[i] 
				answer += 1
			end
		end
		answer
	end
end
