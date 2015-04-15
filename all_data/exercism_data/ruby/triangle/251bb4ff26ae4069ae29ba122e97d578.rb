class Triangle
  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
  end

  def kind
    unless illogical?
      return :equilateral if equilateral?
      return :isosceles   if isosceles?
      return :scalene
    end
  end

  def illogical?
    if @a + @b <= @c || @b + @c <= @a || @c + @a <= @b
      raise TriangleError
    end
  end

  def equilateral?
    @a == @b && @b == @c
  end

  def isosceles?
    @a == @b || @b == @c || @a == @c
  end
end

class TriangleError < Exception
end
