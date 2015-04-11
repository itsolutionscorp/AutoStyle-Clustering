class Grains
	def square num
		return 1 if num == 1
		2 * square(num - 1)
	end

	def total
		18_446_744_073_709_551_615
	end
end
