class Grains

	def initialize
		@squares = setup_squares
	end

	def setup_squares
		board = Array.new(64)
		grains = 1
		0.upto(63) do |i|
			board[i] = grains
			grains = grains * 2
		end
		return board
	end

	def square(index)
		return @squares[index -1]
	end

	def total
		sum = 0
		0.upto(63) do |i|
			sum += @squares[i]
		end
		return sum
	end

end
