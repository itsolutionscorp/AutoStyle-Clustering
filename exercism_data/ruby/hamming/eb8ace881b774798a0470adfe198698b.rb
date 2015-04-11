class Hamming
	def self.compute(first_strand, second_strand)
		#convert strings to arrays
		sequence_one = first_strand.chars
		sequence_two = second_strand.chars
		
		#add 1 to Hamming distance for each difference between sequences
		h_dist = 0
		for i in 0..sequence_one.length
			puts "i: #{i}, h_dist: #{h_dist}"
			if sequence_one[i] != sequence_two[i] 
				then h_dist += 1
			end
		end
		
		#return Hamming distance
		return h_dist
	end
end
