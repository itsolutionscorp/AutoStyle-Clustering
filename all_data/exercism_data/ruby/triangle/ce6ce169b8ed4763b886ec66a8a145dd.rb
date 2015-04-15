class TriangleError < ArgumentError
end

class Triangle

  def initialize(s1, s2, s3)
    @s1 = s1
    @s2 = s2
    @s3 = s3
    valid?
  end

  def kind
    case 
    when equilateral? then :equilateral
    when isosceles? then :isosceles
    when scalene? then :scalene
    end
  end

  private
  def equilateral?
    [@s1, @s2, @s3].uniq.count == 1
  end

  def isosceles? 
    [@s1, @s2, @s3].uniq.count == 2
  end

  def scalene?
    [@s1, @s2, @s3].uniq.count == 3
  end

  def valid?
    contains_negative_numbers? || triangle_inequality?
  end

  def contains_negative_numbers?
    raise TriangleError unless [@s1, @s2, @s3].all? { |s| s > 0 }
  end

  def triangle_inequality?
    raise TriangleError unless [@s1, @s2, @s3].max < [@s1, @s2, @s3].sort.take(2).reduce(:+)
  end

end
