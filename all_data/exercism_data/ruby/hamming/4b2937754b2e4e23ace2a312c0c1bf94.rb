class Hamming
	def self.compute(str1,str2)
		arr_str1 =[]
		arr_str2 = []
		arr_str1 = str1.split(//)
		arr_str2 = str2.split(//)
		max_length = (arr_str1.length > arr_str2.length) ? arr_str2.length : arr_str1.length
		i = 0
		ham_distance =  0
		while i < max_length
			if arr_str1[i] != arr_str2[i]
				ham_distance += 1
			end
			i += 1
		end
		return ham_distance
	end
end
