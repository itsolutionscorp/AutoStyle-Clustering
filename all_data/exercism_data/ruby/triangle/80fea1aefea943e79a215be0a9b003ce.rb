class Triangle

	def initialize(*sides)
		@sides = sides
	end

	def kind
		if invalid? 
			raise TriangleError
		elsif @sides.uniq.length == 1
			:equilateral
		elsif @sides.uniq.length ==2
			:isosceles
		else
			:scalene
		end
	end

	private

	def invalid?
		@sides.uniq[0] == 0 || @sides.any?{|x| x < 0 } || @sides.sort[2] >= (@sides.sort[0]+@sides.sort[1])
	end
end

class TriangleError < StandardError; end
