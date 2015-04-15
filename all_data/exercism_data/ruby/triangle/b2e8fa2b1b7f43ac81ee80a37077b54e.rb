class TriangleError < StandardError
end

class Triangle

  def initialize(first_side, second_side, third_side)
    self.sides = first_side, second_side, third_side
    raise TriangleError if zero_or_negative_side? || !satisfies_triangle_inequality?
  end

  def kind
    @kind ||= case distinct_side_lengths
              when 3
                :scalene
              when 2
                :isosceles
              when 1
                :equilateral
              end
  end

  private

  attr_accessor :sides

  def distinct_side_lengths
    sides.uniq.count
  end

  def zero_or_negative_side?
    sides.any? {|side| side <= 0 }
  end

  def satisfies_triangle_inequality?
    0 < sides.inject(&:+) - 2 * sides.max
  end

end
