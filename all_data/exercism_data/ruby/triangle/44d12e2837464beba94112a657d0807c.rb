class Triangle
  attr_accessor :s1, :s2, :s3

  def initialize(s1, s2, s3)
    @s1, @s2, @s3 = s1, s2, s3
    is_valid?
  end

  def kind
    case 
    when s1 == s2 && s1 == s3 then              :equilateral
    when s1 == s2 || s2 == s3 || s1 == s3 then  :isosceles
    else                                        :scalene
    end
  end

  private

  def is_valid?
    vals = [s1, s2, s3].sort
    raise TriangleError if vals[0] + vals[1] <= vals[2]
    raise TriangleError if vals.any?{|i| i<=0}
  end
end

class TriangleError < StandardError
end
