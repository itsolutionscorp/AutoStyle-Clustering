class Squares

	def initialize value
		@value = value
	end

	def square_of_sums
		@square_of_sums ||= operate( :+ ) ** 2
	end

	def sum_of_squares
		@sum_of_squares ||= operate { |sum, i| sum + i ** 2 } 
	end

	def difference
		square_of_sums - sum_of_squares
	end

	private

	def operate operator = nil, &block
		if block_given?
			1.upto( @value ).inject( &block )
		else
			1.upto( @value ).inject operator
		end
	end

end
