class TriangleError < RuntimeError
end

class Triangle

  TRIANGLES = {1 => :equilateral,
               2 => :isosceles,
               3 => :scalene
  }

  def initialize(length_1, length_2, length_3)
    @length_of_sides = [length_1, length_2, length_3]
  end

  def kind
    if zero_sum? || inequal?
      raise TriangleError.new("This is not a triangle.")
    else
      TRIANGLES[@length_of_sides.uniq.count]
    end
  end

  private

  def zero_sum?
    @length_of_sides.inject { |sum, side| sum + side } == 0
  end

  def inequal?
    side = @length_of_sides.sort
    side[0] + side[1] <= side[2]
  end

end
