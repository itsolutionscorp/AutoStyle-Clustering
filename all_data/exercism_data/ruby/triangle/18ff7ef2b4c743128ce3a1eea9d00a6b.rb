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
    raise if a                                           <= 0 ||
             b                                           <= 0 ||
             c                                           <= 0 ||
             Math.acos((b*b + c*c - a*a) / (2*b*c).to_f) <= 0 ||
             Math.acos((a*a + c*c - b*b) / (2*a*c).to_f) <= 0 ||
             Math.acos((a*a + b*b - c*c) / (2*a*b).to_f) <= 0
  rescue
    raise TriangleError
  end
end
