class Triangle
  attr_reader :sides

  def initialize(*sides)
    @sides = sides
    validate_sides!
  end

  def kind
    case unique_sides
    when 1 then :equilateral
    when 2 then :isosceles
    else :scalene
    end
  end

private

  def validate_sides!
    if sides.any?(&:zero?) || sides.any?(&negative?) ||
        sides.any?(&too_short?)
      raise TriangleError
    end
  end

  def unique_sides
    sides.uniq.length
  end

  def negative?
    proc {|n| n < 0 }
  end

  def too_short?
    proc do |side|
      perimeter - side <= side
    end
  end

  def perimeter
    sides.inject(:+)
  end
end

class TriangleError < StandardError; end
