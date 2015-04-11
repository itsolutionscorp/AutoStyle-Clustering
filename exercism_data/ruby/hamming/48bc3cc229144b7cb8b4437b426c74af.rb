class DNA
	def initialize(strand)
		@strand = strand
	end

	def hamming_distance(another_strand)
		@another_strand = another_strand
		convert_strands_to_array
		reduce_arrays_to_same_size
		distance_between_strands
	end

	private

	def distance_between_strands
		distance = 0
		i = 0
		@strand.each do |element|
			distance += 1 if element != @another_strand[i]
			i += 1
		end
		distance
	end

	def convert_strands_to_array
		@strand = @strand.split(//)
		@another_strand = @another_strand.split(//)
	end

	def reduce_arrays_to_same_size
		if @strand.length > @another_strand.length 
			@strand = @strand.take(@another_strand.length)
		else
			@another_strand = @another_strand.take(@strand.length)
		end
	end


end
