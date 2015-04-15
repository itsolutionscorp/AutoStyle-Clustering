class Triangle
  attr_reader :sides
  def initialize(*sides)
    raise TriangleError unless satisfies_inequality_theorem?(*sides.sort)
    @sides = sides
  end

  def kind
    { 1 => :equilateral,
      2 => :isosceles,
      3 => :scalene }.fetch(sides.uniq.count)
  end

  private

  def satisfies_inequality_theorem?(a, b, c)
    (a + b) > c
  end
end

class TriangleError < StandardError
end
