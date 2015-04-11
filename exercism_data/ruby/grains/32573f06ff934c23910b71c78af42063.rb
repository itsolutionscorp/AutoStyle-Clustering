class Grains
	def initialize
	end
	def square square_num
		number_of_grains = 2 ** (square_num - 1)
	end
	def total
		total_grains = (2 ** 64 - 1)
	end
end
