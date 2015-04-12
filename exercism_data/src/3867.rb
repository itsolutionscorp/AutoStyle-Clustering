class Hamming	
	def compute(str1,str2)
		index = 0
		size = str1.length()<str2.length() ? str1.length() : str2.length()
		diff = 0
		while index < size do
			if str1[index] != str2[index] 
				diff += 1
				end
			index += 1		
			end	
		return diff	
	end
end
