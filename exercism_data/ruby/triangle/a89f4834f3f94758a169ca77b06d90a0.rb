class Triangle

  attr_reader :side1, :side2, :side3, :sides

  def initialize(one, two, three)
    @side1 = one
    @side2 = two
    @side3 = three
    @sides = validated_sides
  end

  def kind
    equilateral || isosceles || :scalene
  end

  private

  def validated_sides
    if valid_triangle?
      [side1, side2, side3]
    else
      raise TriangleError
    end
  end

  def equilateral
    sides.uniq.count == 1 ? :equilateral : false
  end

  def isosceles
    sides.uniq.count == 2 ? :isosceles : false
  end

  def valid_triangle?
    (side1+side2) > side3 && (side2+side3) > side1 && (side1+side3) > side2
  end

end

class TriangleError < StandardError

end
