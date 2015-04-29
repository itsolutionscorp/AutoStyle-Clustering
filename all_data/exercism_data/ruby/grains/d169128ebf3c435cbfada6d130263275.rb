class Grains
	def initialize
		@num_fields = 64
	end
	def square(sq_number)
		2**(sq_number - 1)
	end
	def total
		2**(@num_fields -1) * 2 -1
	end
end
