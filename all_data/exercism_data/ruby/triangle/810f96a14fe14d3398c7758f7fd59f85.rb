class Triangle

  def initialize(*args)
    @sides = args

    raise TriangleError if bad_input? 
  end

  def kind

    case @sides.uniq.count
    when 3 then :scalene
    when 2 then :isosceles
    when 1 then :equilateral
    end
  end

  private

  def bad_input?
    zero_or_less_side? || triangle_inequality_fail?
  end

  def zero_or_less_side?
    @sides.any? { |side| side <= 0 }
  end

  def triangle_inequality_fail?
    @sides.permutation.any? {|side| side[0] + side[1] <= side[2]}
  end
end

class TriangleError < StandardError; end
