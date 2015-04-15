class Hamming
	
	def self.compute(strand1, strand2)
		strand1_array = strand1.downcase.split("")
		strand2_array = strand2.downcase.split("")

		return 0 if strand1_array.length != strand2_array.length

		hamming_difference = 0
		strand1_array.each_with_index do |letter, index|		
			if letter != strand2_array[index]
				hamming_difference += 1
			end  
		end
		return hamming_difference
	end

end


# def get_hamming_difference(strand1, strand2)
# 	strand1_array = strand1.downcase.split("")
# 	strand2_array = strand2.downcase.split("")

# 	hamming_difference = 0
# 	strand1_array.each_with_index do |letter, index|		
# 		if letter != strand2_array[index]
# 			hamming_difference += 1
# 		end  
# 	end
# 	return hamming_difference
# end
