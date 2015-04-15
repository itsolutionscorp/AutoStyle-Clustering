class TriangleError < Exception
end

class Triangle
  private
  
  attr_accessor :a, :b, :c

  public
  
  def initialize(a, b, c)
    self.a, self.b, self.c = a, b, c
    raise TriangleError.new('The sum of the lengths of any two sides of a triangle always exceeds the length of the third side.') unless valid_sides?
  end

  def kind
    case number_of_equal_sides
    when 1
      return :scalene
    when 2
      return :isosceles
    else
      return :equilateral
    end
  end

  def number_of_equal_sides
    count = 1
    count += 1 if a == b
    count += 1 if b == c
    count += 1 if a == c
    count
  end

  def valid_sides?
    # Sum of any two sides of a triangle should always be greater than the third side.
    (a + b > c) && (b + c > a) && (c + a > b)
  end
end
