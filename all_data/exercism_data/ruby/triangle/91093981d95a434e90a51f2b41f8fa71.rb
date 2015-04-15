class Triangle
  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
  end

  def kind
    if (@a <= 0 || @b <= 0 || @c <= 0) ||
       (@a == @b && @c > @a) ||
       (@a == @c && @b > @a) ||
       (@b == @c && @a > @b) ||
       (@a > @b + @c) || (@b > @a + @c) || (@c > @a + @b)
      raise TriangleError
    else
      if @a == @b && @b == @c && @c && @a
        return :equilateral
      elsif (@a == @b && @a != @c) ||
            (@a == @c && @a != @b) ||
            (@b == @c && @a != @b)
        return :isosceles
      else
        return :scalene
      end
    end
  end
end

class TriangleError < StandardError
end
