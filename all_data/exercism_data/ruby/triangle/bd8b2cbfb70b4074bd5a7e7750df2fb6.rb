class Triangle
	def initialize(side_a,side_b,side_c)
		if side_a + side_b <= side_c || side_a + side_c <= side_b || side_b + side_c <= side_a 
			raise TriangleError
		else
			@side_a = side_a
			@side_b = side_b
			@side_c = side_c
		end

	end

	def kind
		if @side_a == @side_b && @side_b == @side_c
			:equilateral
		

		elsif (@side_a == @side_b && @side_a != @side_c) || (@side_c == @side_b && @side_a != @side_c)|| (@side_c == @side_a && @side_b != @side_c)
			:isosceles
		else
			:scalene
		end
	end
end
