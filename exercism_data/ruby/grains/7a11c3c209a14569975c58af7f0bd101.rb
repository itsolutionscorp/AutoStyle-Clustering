class Grains
	attr_accessor :board

	def initialize
		@board = [0]

		seed = 1
		
		for i in 1..64
			@board[i] = seed
			seed = seed * 2
		end
	end

	def square(n)
		return @board[n]
	end

	def total
		sum = 0

		@board.each { |x| sum += x }

		return sum
	end
end
