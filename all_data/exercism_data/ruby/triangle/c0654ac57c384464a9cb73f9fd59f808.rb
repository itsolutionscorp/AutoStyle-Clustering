class TriangleError < StandardError
end

class Triangle
  def initialize(a, b, c)
    @a, @b, @c = [a, b, c].sort
    raise TriangleError unless valid
  end

  def kind
    case [@a, @b, @c].uniq.length
    when 1
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

  private
  def valid
    case
    when [@a, @b, @c].min < 0, [@a, @b, @c].include?(0), @a + @b <= @c
      false
    else
      true
    end
  end

end
