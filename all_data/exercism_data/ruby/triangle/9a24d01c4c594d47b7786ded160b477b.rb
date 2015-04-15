require 'set'

class TriangleError < StandardError
end

class Triangle
  attr_reader :sides
  KINDS = [:_, :equilateral, :isosceles, :scalene]

  def initialize(a, b, c)
    @sides = [a, b, c].sort
  end

  def kind
    case sides
    when violates_triangle_inequality? 
      raise TriangleError, "violates triangle inequality"
    when illegal_sides?
      raise TriangleError, "side lengths must be greater than zero"
    else
      KINDS[sides.uniq.count]
    end  
  end

  private
  def violates_triangle_inequality?
    ->(sides) {sides[0] + sides[1] <= sides[2]}
  end

  def illegal_sides?
    ->(sides) {sides.first <= 0}
  end
end
