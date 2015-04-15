require "pry"

class Triangle
  def initialize(side1, side2, side3)
    validate(side1,side2,side3)
    @side1 = side1
    @side2 = side2
    @side3 = side3
  end

  def kind
    if @side1==@side2 && @side1==@side3
      :equilateral
    elsif @side1 == @side2 || @side1 == @side3 || @side2 == @side3
      :isosceles
    else
      :scalene
    end
  end

  private

  def validate(s1,s2,s3)
    unless sides_greater_than_zero?(s1,s2,s3) && sum_of_sides_valid?(s1,s2,s3)
      raise TriangleError
    end
  end

  def sides_greater_than_zero?(s1,s2,s3)
    s1 > 0 && s2 > 0 && s3 > 0
  end

  def sum_of_sides_valid?(s1,s2,s3)
    s1 < s2 + s3 && s2 < s1 + s3 && s3 < s2 + s1
  end

end

class TriangleError < StandardError
end
