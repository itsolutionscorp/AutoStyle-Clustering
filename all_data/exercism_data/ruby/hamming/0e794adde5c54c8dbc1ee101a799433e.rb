class DNA

	def initialize(strand)
		@strand = strand
	end

	def hamming_distance(test_strand)
		strand_array = @strand.split("")
		test_strand_array = test_strand.split("")

		test_strand_array.each.with_index.count do |point, index|	
			test_strand_array[index] != strand_array[index] && strand_array[index] != nil
		end
	end
end
