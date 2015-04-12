class Hamming
	def compute(str1, str2)
		
		min = [str1.length, str2.length].min

		count = 0
		i = 0

		while i < min 
			if str1[i] != str2[i]
				count += 1
			end
			i+=1
		end

		count
	end

end
