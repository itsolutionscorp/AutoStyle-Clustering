class Triangle

  attr_reader :left, :right, :bottom, :sides

  def initialize(left, right, bottom)
    @left = left
    @right = right
    @bottom = bottom
    @sides = [left, right, bottom]
  end

  def kind
    raise TriangleError if @sides.any? { |side| side <= 0 }
    raise TriangleError if inequal?
    return :equilateral if @sides.uniq.count == 1
    return :isosceles if @sides.uniq.count == 2
    return :scalene if @sides.uniq.count == 3
  end

  def inequal?
    max = @sides.sort.last
    if @sides.sort[0] + @sides.sort[1] <= max
      return true
    else
      false
    end
  end

end

class TriangleError < StandardError

end
