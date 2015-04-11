class Triangle

  def initialize(*args)
    @sides = args

    raise TriangleError if bad_input? 
  end

  def kind
    result = @sides.each_with_object(Hash.new(0)) {|side, sum| sum[side] += 1}

    return :equilateral if result.values.include?(3)
    return :isosceles if result.values.include?(2)

    :scalene
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
