class Triangle
  attr_reader :kind

  def initialize(*sides)
    @sides = sides.sort
    raise TriangleError if error?
    @kind = find_kind
  end

private

  def find_kind
    case @sides.uniq.count
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    else raise TriangleError
    end
  end

  def error?
    return true if @sides.any? { |s| s <= 0 }
    return true if @sides[0] + @sides[1] <= @sides[2]
    false
  end
end

class TriangleError < StandardError
end
