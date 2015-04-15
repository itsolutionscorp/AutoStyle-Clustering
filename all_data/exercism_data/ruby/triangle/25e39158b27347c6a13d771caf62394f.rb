class TriangleError < StandardError; end

class Triangle 
  attr_reader :side_one, :side_two, :side_three

  def initialize(side_one, side_two, side_three)
    @side_one = side_one
    @side_two = side_two
    @side_three = side_three
  end

  def kind
    if invalid_side? || violates_inequality?
      raise TriangleError
    elsif equilateral? 
      :equilateral
    elsif isoscoles? 
      :isosceles
    else
      :scalene
    end
  end

private

	def equilateral?
		[side_one, side_two, side_three].uniq.count == 1  		
	end

	def isoscoles?
		[side_one, side_two, side_three].uniq.count == 2
	end

  def invalid_side?
    [side_one, side_two, side_three].any? { |side| side <= 0} 
  end

  def violates_inequality?
		sorted_triangle = [side_one, side_two, side_three].sort
		sorted_triangle[0] + sorted_triangle[1] <= sorted_triangle[2]
  end

end
