class Grains
	def self.initialize
		@grains_per_square = grains_per_square 
		@total_grains = total_grains
	end

	def square(number)
		grains_per_square = 2 **(number-1)
	end

	def total
		squares =*(1..64)
		total_grains = squares.map { |x| 
			2**(x-1)
		}.inject(0, &:+) 
	end
end
