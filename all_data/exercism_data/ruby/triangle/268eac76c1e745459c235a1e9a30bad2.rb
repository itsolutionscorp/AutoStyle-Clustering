class Triangle
  def initialize(side1, side2, side3)
    @side1, @side2, @side3 = side1, side2, side3
    raise TriangleError unless valid?
  end

  def kind
    {
      1 => :equilateral,
      2 => :isosceles,
      3 => :scalene,
      }[number_of_equal_length_sides]
  end

  private
  def number_of_equal_length_sides
    [@side1, @side2, @side3].uniq.size
  end

  private
  def valid?
    all_sides_are_greater_than_zero? && no_triangle_inequality?
  end

  private
  def all_sides_are_greater_than_zero?
    [@side1, @side2, @side3].all? { |side| side > 0 }
  end

  private
  def no_triangle_inequality?
    [@side1, @side2, @side3].all? { |side| side < (sum_of_sides - side) }
  end

  def sum_of_sides
    @sum_of_sides ||= @side1 + @side2 + @side3
  end

end

class TriangleError < StandardError; end
