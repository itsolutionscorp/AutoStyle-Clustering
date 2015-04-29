TriangleError = Class.new StandardError

class Triangle
  def initialize(a, b, c)
    self.a, self.b, self.c = a, b, c
    validate_sides!
  end

  def kind
    @kind ||= case [a, b, c].uniq.size
              when 1 then :equilateral
              when 2 then :isosceles
              when 3 then :scalene
              end
  end

  private

  attr_accessor :a, :b, :c

  def validate_sides!
    raise TriangleError if a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a
  end
end
