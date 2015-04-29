class TriangleError < Exception
end

class Triangle
  attr_accessor :kind

  def initialize(a, b, c)
    verify_triangle(a, b, c)
    self.kind = find_type_triangle(a, b, c) 
  end

  def verify_triangle(a, b, c)
    if !(a + b > c) or !(a + c > b) or !( b  +  c > a) #checks for triangle inequality
      raise TriangleError
    end
  end

  def find_type_triangle(a, b, c)
    sides = []
    sides << a 
    sides << b 
    sides << c 
    sides.sort! #sorting allows easy way to compare isosceles triangles

    if sides[0] == sides[1] && sides[1] == sides[2]
      return :equilateral
    elsif sides[1] == sides[0] || sides[1] == sides[2]
      return :isosceles
    else
      return :scalene 
    end

  end
end
