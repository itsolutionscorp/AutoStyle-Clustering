class Triangle

	def initialize(a, b, c)
		@triangle = [a, b, c].sort
		raise TriangleError if @triangle[0] <= 0 || @triangle[0] + @triangle[1] <= @triangle[2]
	end

	def kind
		return case @triangle.uniq.size
		when 1 then :equilateral
		when 2 then :isosceles
		when 3 then :scalene
		end
	end

end

class TriangleError < StandardError
end
