class Array
	def except_at(index)
		self.select.with_index { |v, i| i != index }
	end
end

class TriangleError < ArgumentError
end

class Triangle

	KINDS = [:impossible, :equilateral, :isosceles, :scalene]

	def initialize(side_a, side_b, side_c)
		@sides = [side_a, side_b, side_c]
		raise TriangleError if invalid_sides?
		raise TriangleError unless inequal?
	end
	
	def kind
		KINDS[@sides.group_by { |side| side }.count]
	end
private

	def invalid_sides?
		@sides.any? { |side| side <= 0 }
	end
	
	def inequal?
		@sides.select.with_index do |side, index|
			side < @sides.except_at(index).reduce(:+)
		end.count == 3
	end
end
