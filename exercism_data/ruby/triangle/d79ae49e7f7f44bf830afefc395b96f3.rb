class Triangle
  attr_reader :sides, :unique_sides

  def initialize(one, two, three)
    @sides = one, two, three
    @unique_sides = sides.uniq.count
  end

  def kind
    raise TriangleError if invalid?
    case unique_sides
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end

  def invalid?
    sides.min <= 0 || sides.sort.first(2).reduce(:+) <= sides.max
  end

end

class TriangleError < ArgumentError
end
