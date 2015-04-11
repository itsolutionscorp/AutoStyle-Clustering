class TriangleError < StandardError; end

class Triangle
  attr_reader :sides

  def initialize(a, b, c)
    @sides = [a, b, c]
    test_legality
  end

  def kind
    case sides.uniq.size
    when 1
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

  private
    def test_legality
      raise TriangleError if no_area? ||
        negative_side_exists? ||
        violates_triangle_inequality?
    end

    def no_area?
      sides.all?(&:zero?)
    end

    def negative_side_exists?
      sides.any?{|side| side < 0}
    end

    def violates_triangle_inequality?
      sides.any?{|side| side >= sides.inject(:+) - side}
    end
end
