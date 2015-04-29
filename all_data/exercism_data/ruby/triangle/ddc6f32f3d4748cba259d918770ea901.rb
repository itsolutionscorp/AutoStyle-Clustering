class Triangle

  attr_reader :sides

  def initialize(s1,s2,s3)
    @sides = [s1,s2,s3]
    raise TriangleError if invalid_sides?
  end

  def kind 
    return :equilateral if equilateral?
    return :isosceles if isosceles?
    :scalene
    # return :scalene if scalene?
  end

  def equilateral?
    @sides.uniq.size == 1
  end

  def isosceles?
    @sides.uniq.size == 2
  end

  def invalid_sides?
    zero_or_less? || inequality? 
  end

  def zero_or_less?
    @sides.any?{|e| e <= 0}
  end

  def inequality?
    sorted_sides = @sides.sort
    sorted_sides[0] + sorted_sides[1] <= sorted_sides[2]
  end

end

class TriangleError < StandardError
end
