class TriangleError < StandardError
end

class Triangle
  def initialize(a, b, c)
    @a = a
    @b = b
    @c = c
    @sides = [@a, @b, @c]
    valid_triangle
  end

  def kind
    if equilateral?
      :equilateral
    elsif isosceles?
      :isosceles
    elsif scalene?
      :scalene
    end
  end

  def valid_triangle
    raise TriangleError if nonzero? || inequality?
  end


  private

  def equilateral?
    @sides.uniq.count == 1
  end

  def isosceles?
    @sides.uniq.count == 2
  end

  def scalene?
    @sides.uniq.count == 3
  end

  def nonzero?
    @sides.each { |el| return true if el <= 0 }
    false
  end

  def inequality?
    sort = @sides.sort
    sort[0] + sort[1] <= sort[2]
  end

end
