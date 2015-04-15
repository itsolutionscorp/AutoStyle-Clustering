TriangleError = Class.new(StandardError)

class Triangle 
  def initialize(a, b, c)
    @sides = [a, b, c]
    validate!
    @kind = case @sides.uniq.count
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end
  
  attr_reader :kind
  
  private
  
  def validate!
    fail TriangleError, 'Sides must be positive' if @sides.min <= 0
    fail TriangleError, 'Triangle inequality fail' if inequality_illegal?
  end
  
  def inequality_illegal?
    perimeter = @sides.reduce :+
    @sides.any? { |s| s >= perimeter - s }
  end
end
