class Grains

	def square(id)
		2 ** (id-1)
	end

	def total
		2 * square(64)-1
	end

end
