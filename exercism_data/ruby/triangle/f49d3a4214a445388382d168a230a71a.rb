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
    elsif [side_one, side_two, side_three].uniq.count == 1  
      :equilateral
    elsif [side_one, side_two, side_three].uniq.count == 2
      :isosceles
    else
      :scalene
    end
  end

private

  def invalid_side?
    [side_one, side_two, side_three].any? { |side| side <= 0} 
  end

  def violates_inequality?
    side_one + side_two <= side_three ||
    side_one + side_three <= side_two ||
    side_two + side_three <= side_one
  end

end
