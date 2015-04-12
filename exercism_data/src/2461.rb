class Hamming
	def compute(str1,str2)
		i = 0
		h = 0
		while i < str1.length do
			if (str1[i] != str2[i]) 
				h += 1
			end
			i += 1
		end
		h
	end
end
