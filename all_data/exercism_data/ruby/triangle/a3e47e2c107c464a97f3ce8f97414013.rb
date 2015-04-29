class TriangleError < RuntimeError
end

class Triangle
  def initialize(num, num2, num3)
    @num = num
    @num2 = num2
    @num3 = num3
  end

  def kind
    sides = [@num, @num2, @num3].uniq.count
    if zero_sum? || negative_side? || inequal?
        raise TriangleError
    else
      case sides
        when 1
          :equilateral
        when 2
          :isosceles
        when 3
          :scalene
      end
    end
  end

private

  def zero_sum?
    @num + @num2 + @num3 == 0
  end

  def negative_side?
    [@num, @num2, @num3].any? {|num| num < 0}
  end

  def inequal?
    nums = [@num, @num2, @num3].sort
    nums[0] + nums[1] <= nums[2]
  end

end
