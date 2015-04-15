class	Triangle
	def initialize(a, b, c)
		@sides = [a, b, c].sort
	end

	def kind
		guard_against_invalid_lengths
		return :equilateral if @sides[0] == @sides[1] and @sides[1] == @sides[2]
		return :isosceles if @sides[0] == @sides[1] || @sides[0] == @sides[2] || @sides[1] == @sides[2]
		return :scalene
	end

	def guard_against_invalid_lengths
		if @sides[0] + @sides[1] <= @sides[2]
      raise TriangleError
    end
	end

end

class TriangleError < StandardError  
end
