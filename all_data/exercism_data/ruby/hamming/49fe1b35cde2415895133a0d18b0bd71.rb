class Hamming 
	def self.compute(str1, str2)
		i=0
		ham = 0
		if str1.length < str2.length
			lon = str1
		else
			lon = str2
		end 
		while i < lon.length
			if str1[i] != str2[i]
				ham += 1
			end
			i+=1
		end
		return ham
	end
end
