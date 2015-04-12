class Hamming

	def compute(strand1, strand2)
		#convert string paramaters to array of characters
		strand1_array = strand1.chars.to_a
		strand2_array = strand2.chars.to_a

		#initialize distance counter
		distance = 0

		#only check the distance up to the shorter of the 2 sequences
		strand1_array.length <= strand2_array.length ? length = strand1_array.length : length = strand2_array.length

		# iterate over the arrays
		for i in 0..length-1
			# if the sequences have different characters at the same position, increment the Hamming distance
			if strand1_array[i] != strand2_array[i] 
				distance += 1
			end
		end

		return distance
	end

end
