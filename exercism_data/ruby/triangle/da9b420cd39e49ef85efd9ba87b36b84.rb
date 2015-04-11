class Triangle

	def initialize(a, b, c)
		@triangle = [a, b, c].sort
		raise TriangleError if @triangle[0] <= 0 || @triangle[0] + @triangle[1] <= @triangle[2]
	end

	def kind
		return :equilateral if @triangle.uniq.size == 1
		return :isosceles 	if @triangle.uniq.size == 2
		return :scalene		if @triangle.uniq.size == 3
	end

end

class TriangleError < StandardError

	begin
		raise TriangleError
	rescue Exception
		puts "This isn't a triangle!"
	end

end
