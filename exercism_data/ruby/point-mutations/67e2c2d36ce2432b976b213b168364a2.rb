class DNA

	def initialize dna

		if dna.empty?
			@dna = dna 
		elsif not dna.is_a?String
			raise ArgumentError, "Value must be a string"
		else
			@dna = dna.to_s
		end
	end

	def hamming_distance dna_to_compare
		if dna_to_compare.empty? or @dna.empty?
			return 0
		elsif not dna_to_compare.is_a?String
			raise ArgumentError, "Value must be a string"
		end
		hamming_distance = 0
		end_point = @dna.length
		length_difference = @dna.length - dna_to_compare.length

		if length_difference > 0
			end_point = dna_to_compare.length
		end
		
		end_point -= 1;

		for i in ( 0..end_point )
		       if @dna[i] != dna_to_compare[i]
		       		hamming_distance += 1
		 	end
       		end

		return hamming_distance

	end

end
