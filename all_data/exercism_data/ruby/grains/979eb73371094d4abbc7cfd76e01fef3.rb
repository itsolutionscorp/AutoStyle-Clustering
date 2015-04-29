class Grains
	def square(cell_index)
		2 ** (cell_index - 1)
	end

	def total 
		(2 ** 64) - 1
	end
end
