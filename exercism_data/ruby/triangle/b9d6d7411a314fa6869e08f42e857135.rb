class TriangleError < RuntimeError
end

class Triangle
  TRIANGLES = { 1 => :equilateral, 2 => :isosceles, 3 => :scalene }
  def initialize(length_1, length_2, length_3)
    @length_of_sides = [length_1, length_2, length_3]
  end

  def kind
    sides_w_equal_length = @length_of_sides.uniq.count
    if zero_sum? || negative_side? || inequal?
        raise TriangleError
    else
     TRIANGLES[sides_w_equal_length]
    end
  end

private

  def zero_sum?
    @length_of_sides.inject{|sum,side| sum + side } == 0
  end

  def negative_side?
    @length_of_sides.any? {|side| side < 0}
  end

  def inequal?
    side = @length_of_sides.sort
    side[0] + side[1] <= side[2]
  end

end
