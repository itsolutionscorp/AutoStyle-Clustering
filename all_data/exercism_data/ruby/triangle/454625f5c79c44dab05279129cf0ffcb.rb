class TriangleError < StandardError
end

class Triangle
  def initialize *sides
    @sides = sides
    fail TriangleError unless valid?
  end

  def valid?
    return false if @sides.length != 3

    return false unless @sides.all? {|side| side > 0}
    sorted_sides = @sides.sort
    sorted_sides[0] + sorted_sides[1] > sorted_sides[2]
  end

  def kind
    case nr_of_lengths
    when 1 then :equilateral
    when 2 then :isosceles
    else        :scalene
    end
  end

  def nr_of_lengths
    @sides.group_by {|x| x }.length
  end
end
