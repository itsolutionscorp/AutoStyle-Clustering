class TriangleError < Exception
end

class Triangle
  
  def initialize(*sides)
    @sides = sides
  end

  def kind
    validate
    return :equilateral if equilateral?
    return :isosceles if isosceles?
    return :scalene if scalene?
  end
  
  def validate
    raise TriangleError if negative_or_zero_sides? || triangle_inequality?
  end

  def equilateral?
    @sides.combination(2).all? {|side1, side2| side1 == side2}
  end

  def isosceles?
    @sides.combination(2).any? {|side1, side2| side1 == side2}
  end

  def scalene?
    @sides.combination(2).none? {|side1, side2| side1 == side2}
  end

  def negative_or_zero_sides?
    @sides.any? {|side| side <= 0}
  end

  def triangle_inequality?
    @sides.permutation.any? {|a, b, c| a + b <= c}
  end

end
