class Hamming
	
	class << self

		def compute(strand_a, strand_b)
			strand_size_to_match(strand_a, strand_b)
			count_difference(strand_a.upcase.slice(0, @size_to_match), strand_b.upcase)			
		end

		private

		def strand_size_to_match(strand_a, strand_b)
			@size_to_match = [strand_a.size, strand_b.size].min
		end

		def count_difference(strand_a, strand_b)
			strand_a.chars.zip(strand_b.chars).count { |letter_a, letter_b| letter_a != letter_b }
		end

	end

end
