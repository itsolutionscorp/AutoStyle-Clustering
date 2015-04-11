class Series
	
	def initialize(sequence)
		@sequence = sequence
	end

	def digits
		@sequence.chars.map(&:to_i)
	end

	def slices(n)
		digits.each_cons(n).map { |slice| slice }
	end

	def largest_product(n)
		raise ArgumentError if n > @sequence.length
		
		if n == 0
			1
		else
			slices(n).map { |slice| slice.reduce(:*) }.max
		end
	end

end
