class TriangleError < StandardError; end

class Triangle
  def initialize(a, b, c)
    @sides = [a, b, c]
    raise TriangleError if has_invalid_side? || violates_equality_rule?
  end

  def kind
    case @sides.uniq.length
    when 1 then :equilateral
    when 2 then :isosceles
    else :scalene
    end
  end

  private

  def has_invalid_side?
    @sides.any? { |x| x <= 0 }
  end

  def violates_equality_rule?
    a, b, c = @sides
    (a + b <= c) || (a + c <= b) || (b + c <= a)
  end
end
