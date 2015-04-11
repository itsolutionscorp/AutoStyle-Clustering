class Triangle
  def initialize(*sides)
    @a, @b, @c = sides.sort
    raise TriangleError if @a <= 0 || @a + @b <= @c
  end

  def kind
    case [@a, @b, @c].uniq.size
    when 1 then :equilateral
    when 2 then :isosceles
    else        :scalene
    end
  end
end

class TriangleError < Exception
end
