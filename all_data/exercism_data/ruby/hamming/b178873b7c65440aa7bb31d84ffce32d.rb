class Hamming
	def self.compute(first_strand,second_strand)
		first_strand_array = first_strand.split('')
		second_strand_array = second_strand.split('') 
		diff_counter = 0
		counter = 0
		if first_strand_array.length >= second_strand_array.length
			length = second_strand_array.length
		else
			length = first_strand_array.length
		end
		while counter < length
			if first_strand_array[counter] != second_strand_array[counter]
					diff_counter += 1
			end
			counter +=1
		end
		diff_counter
	end
end
