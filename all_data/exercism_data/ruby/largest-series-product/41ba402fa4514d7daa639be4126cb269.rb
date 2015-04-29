class Series
	
	attr_reader :digits

	def initialize(number_string)
		@digits = number_string.chars.map(&:to_i)
	end
	
	def slices(size)
		@digits.each_cons(size).to_a
	end
	
	def largest_product(size)
		raise ArgumentError if too_big?(size)
		return 1 if identity?(size)
		slices(size).map { |slice| slice.reduce(:*) }.max
	end

private
	def identity?(size)
		(@digits.count * size) == 0
	end
	
	def too_big?(size)
		size > @digits.count
	end
end
