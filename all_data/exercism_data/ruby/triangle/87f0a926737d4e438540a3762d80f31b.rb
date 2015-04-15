class Triangle

	def initialize(x, y, z)
		@x = x
		@y = y
		@z = z
	end

	def kind
		raise TriangleError if not_valid_triangle?
		return :equilateral if equilateral?
		return :isosceles if isosceles?
		return :scalene if scalene?
	end

	def not_valid_triangle?
		true if sorted[0] <= 0 #no negative numbers
		true if sorted[0] + sorted[1] <= sorted[2] #not valid triangle
	end

	def equilateral?
		@x == @y && @x == @z
	end

	def isosceles?
		sorted[1] == sorted[0] || sorted[1] == sorted[2]
	end

	def scalene? 
		sorted[1] != sorted[0] && sorted[1] != sorted[2]
	end

	def sorted
		[@x, @y, @z].sort
	end

end

class TriangleError < StandardError; end
