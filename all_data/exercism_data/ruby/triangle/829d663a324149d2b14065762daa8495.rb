class Triangle  
  def initialize(a, b, c)
    @side_a = a
    @side_b = b 
    @side_c = c 
  end

  def sides
    [@side_a, @side_b, @side_c]
  end

  def perimeter
    sides.inject(:+)
  end

  def valid?
    sides.all? { |length| 2*length < perimeter }
  end

  def kind
    raise TriangleError unless valid?
    case sides.uniq.count
    when 1
      :equilateral
    when 2
      :isosceles
    when 3 
      :scalene
    end
  end
end

class TriangleError < StandardError; end
