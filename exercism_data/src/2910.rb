class Hamming
	def compute(str1, str2)
		min_length = [str1.length, str2.length].min
		counter = 0
		i = 0
		while i < min_length do
			counter+=1 unless str1[i] == str2[i]
			i += 1
		end
		counter
	end
end
