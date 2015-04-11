class TriangleError < StandardError; end

class Triangle
	attr_reader :kind

	def initialize(a, b, c)
		raise(TriangleError) if a+b<=c || a+c<=b || b+c<=a

		if a==b && a==c
			@kind = :equilateral
		elsif a==b || a==c || b==c
			@kind = :isosceles
		else
			@kind = :scalene
		end
	end
end
