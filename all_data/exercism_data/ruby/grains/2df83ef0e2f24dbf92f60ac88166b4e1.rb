class Grains
	def initialize
	end

	def square(square_number)
		2 ** (square_number - 1)
	end

	def total
		grains = 0
		i = 1
		while i <= 64
			grains = grains + (square(i))
			i += 1
		end
		grains
	end

end
