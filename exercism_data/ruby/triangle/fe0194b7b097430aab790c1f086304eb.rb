class Triangle
	def initialize(*sides)
		@sides = sides
		raise TriangleError unless passesTriangleInequality?
	end

	def kind
		case @sides.uniq.count
		when 1 then :equilateral
		when 2 then :isosceles
		when 3 then :scalene
		end
	end

	private
	
		def passesTriangleInequality?
			@sides[0]+@sides[1] > @sides[2] &&
			@sides[0]+@sides[2] > @sides[1] &&
			@sides[1]+@sides[2] > @sides[0]
		end

end

class TriangleError < ArgumentError; end
