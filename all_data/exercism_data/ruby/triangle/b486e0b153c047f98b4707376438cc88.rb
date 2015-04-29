class Triangle
  def initialize(side1, side2, side3)
    @side1 = side1
    @side2 = side2
    @side3 = side3
  end

  def kind
    raise TriangleError unless valid?
    return :equilateral if equilateral?
    return :isosceles   if isosceles?
    return :scalene
  end

  private

  def valid?
    @side1 > 0 &&
    @side2 > 0 &&
    @side3 > 0 &&
    @side1 + @side2 > @side3 &&
    @side2 + @side3 > @side1 &&
    @side3 + @side1 > @side2
  end

  def equilateral?
    @side1 == @side2 && @side2 == @side3
  end

  def isosceles?
    @side1 == @side2 || @side2 == @side3 || @side1 == @side3
  end
end

class TriangleError < StandardError
end
