class Triangle
	def initialize(*sides)
		@sides = sides
		raise TriangleError unless passesTriangleInequality?
	end

	def kind
		case
		when equilateral? 	then :equilateral
		when isosceles? 	then :isosceles
		else					 :scalene
		end
	end

	def equilateral?
		equal_pairs? 3
	end

	def isosceles?
		equal_pairs? 1
	end

	private

		def equal_pairs?(n)
			n == @sides.combination(2).count { |a, b| a == b }
		end
	
		def passesTriangleInequality?
			@sides[0]+@sides[1] > @sides[2] &&
			@sides[0]+@sides[2] > @sides[1] &&
			@sides[1]+@sides[2] > @sides[0]
		end

end

class TriangleError < ArgumentError; end
