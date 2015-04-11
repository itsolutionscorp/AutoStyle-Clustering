class Hamming
	
	def self.compute(strand1, strand2)

		standardize_strands(strand1, strand2)
		
		if check_lengths == false
			return 0
		else
			return get_hamming_difference
		end

	end

	def self.standardize_strands(strand1, strand2)
		@strand1_array = strand1.downcase.split("")
		@strand2_array = strand2.downcase.split("") 
	end

	def self.check_lengths
		@strand1_array.length == @strand2_array.length ? true : false
	end

	def self.get_hamming_difference
		hamming_difference = 0
		@strand1_array.each_with_index do |letter, index|		
			if letter != @strand2_array[index]
				hamming_difference += 1
			end  
		end
		return hamming_difference
	end

end
