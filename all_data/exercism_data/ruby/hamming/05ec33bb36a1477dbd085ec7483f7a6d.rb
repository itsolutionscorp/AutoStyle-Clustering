class Hamming
	def self.compute(str1,str2)
		arr_str1 = str1.split(//)
		arr_str2 = str2.split(//)
		
		arr_str2, arr_str1 = arr_str1, arr_str2 if arr_str1.length > arr_str2.length

		ham_distance = 0
		arr_str1.each_index do |i|
			ham_distance +=1 if arr_str1[i] != arr_str2[i]
		end
		return ham_distance


	end
end
