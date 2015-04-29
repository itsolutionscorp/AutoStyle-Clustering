def compute(nucleotied_a, nucleotied_b)

		@dna_strand_one = []
		@dna_strand_two = []
		@hamming_distance = 0

		nucleotied_a.each_char { |nucleotoid| @dna_strand_one << [nucleotoid] }
		nucleotied_b.each_char { |nucleotoid| @dna_strand_two << [nucleotoid] }

		@enum_dna_strand_one = @dna_strand_one.to_enum
		@enum_dna_strand_two = @dna_strand_two.to_enum

		loop do

			if @enum_dna_strand_one.peek != @enum_dna_strand_two.peek
			   @hamming_distance += 1
			end

			@enum_dna_strand_one.next
			@enum_dna_strand_two.next

		end

		return @hamming_distance
	end