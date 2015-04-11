class TriangleError < StandardError; end


class Triangle
  def initialize(s1, s2, s3)
    @s1=s1.to_f
    @s2=s2.to_f
    @s3=s3.to_f
  end

  def kind
      raise TriangleError.new if @s1 <= 0
      raise TriangleError.new if @s2 <= 0
      raise TriangleError.new if @s3 <= 0
      raise TriangleError.new if (@s1 +@s2) <= @s3
      raise TriangleError.new if (@s3 +@s2) <= @s1
      raise TriangleError.new if (@s1 +@s3) <= @s2
  if
    @s1 != @s2 && @s1 != @s3 && @s2 != @s3
      :scalene
    elsif
    (@s1 + @s2)/ @s3 == 2
      :equilateral
    else
      @s1 == @s2 || @s3
      :isosceles

    end
  end
end
