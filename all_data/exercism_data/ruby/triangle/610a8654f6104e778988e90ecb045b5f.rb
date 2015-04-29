class TriangleError < ArgumentError; end

class Triangle
  KINDS = [:equilateral, :isosceles, :scalene]

  attr_reader :sides

  def initialize(a, b, c)
    @sides = [a, b, c]
    validate_sides
  end

  def kind
    KINDS[sides.uniq.count - 1]
  end

  private

  def validate_sides
    if any_side_with_invalid_length? || violation_of_inequality_theorem?
      raise TriangleError.new
    end
  end

  def any_side_with_invalid_length?
    sides.any? {|side| side <= 0}
  end

  def violation_of_inequality_theorem?
    max_side >= sum_of_two_smaller_sides
  end

  def sum_of_two_smaller_sides
    sides.sort.take(2).inject(:+)
  end

  def max_side
    sides.max
  end
end
