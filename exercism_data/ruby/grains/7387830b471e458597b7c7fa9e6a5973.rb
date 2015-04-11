class Grains
	def initialize(num_squares = 64)
		@num_squares = 64
	end
	def square(n)
		2.to_the(n-1)
	end
	def total
		square(@num_squares + 1) - 1
	end
end

class Fixnum
	def to_the(n)
		self**n
	end
end
