class Triangle
  attr_accessor :s1, :s2, :s3

  def initialize(s1, s2, s3)
    @s1, @s2, @s3 = s1, s2, s3
    is_valid?
  end

  def kind
    case 
    when s1 == s2 && s1 == s3
      :equilateral
    when s1 != s2 && s2 != s3 && s1 != s3
      :scalene
    else
      :isosceles
    end
  end

  def is_valid?
    vals = [s1, s2, s3].sort
    raise TriangleError unless vals[0] + vals[1] > vals[2]
    raise TriangleError unless @s1 > 0 && @s2 > 0 && @s3 > 0
  end

end

class TriangleError < StandardError
end
