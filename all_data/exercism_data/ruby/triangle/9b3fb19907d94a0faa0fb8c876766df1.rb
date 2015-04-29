class TriangleError < StandardError; end

class Triangle
	def initialize(a,b,c)
		@a,@b,@c = a,b,c

		raise TriangleError if [@a,@b,@c].min <= 0
		raise TriangleError unless @a+@b > @c && @a+@c > @b && @b+@c > @a
	end

	def kind
		return :equilateral if @a == @b && @b == @c
		return :isosceles if @a == @b || @b == @c || @a == @c
		return :scalene
	end
end
