class TriangleError < StandardError; end

class Triangle
  def initialize(a, b, c)
    @sides = [a, b, c].sort
    raise TriangleError unless valid?
  end

  def kind
    case
    when equilateral?
      :equilateral
    when isosceles?
      :isosceles
    when scalene?
      :scalene
    end
  end

  private

  def equilateral?
    @sides.uniq.size == 1
  end

  def isosceles?
    @sides.uniq.size == 2
  end

  def scalene?
    @sides.uniq.size == 3
  end

  def valid?
    positive_lengths? && satisfies_triangle_equality?
  end

  def positive_lengths?
    @sides.select { |length| length > 0 }.size == 3
  end

  def satisfies_triangle_equality?
    @sides[0] + @sides[1] > @sides[2]
  end
end
