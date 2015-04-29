class TriangleError < RuntimeError
end

class Triangle
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def kind
    if illegal?
      raise TriangleError
    elsif equilateral?
      :equilateral
    elsif isosceles?
      :isosceles
    else
      :scalene
    end
  end

  def illegal?
    true if no_size? || inequality?
  end

  def no_size?
    true if @a == 0 || @b == 0 || @c == 0
  end

  def inequality?
    true if @a + @b <= @c || @b + @c <= @a || @a + @c <= @b
  end

  def equilateral?
    true if @a == @b && @b == @c
  end

  def isosceles?
    true if @a == @b || @b == @c || @c == @a
  end
end
