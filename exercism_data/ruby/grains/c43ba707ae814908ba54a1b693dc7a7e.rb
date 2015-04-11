class Grains
	attr_reader:total
	def initialize
		@squares = [*0..63].map{|x| 2**x}
		@total = @squares.reduce(:+)
	end

	def square(x)
		@squares[x-1]
	end
end
