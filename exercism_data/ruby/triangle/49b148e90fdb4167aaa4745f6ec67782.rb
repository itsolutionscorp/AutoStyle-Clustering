class Triangle

  def initialize(s_1, s_2, s_3)
    @side_1 = s_1
    @side_2 = s_2
    @side_3 = s_3
  end

  def kind
  raise TriangleError, "Sides must by numbers greater than zero" if illegal_size?
  raise TriangleError, "No two sides can add to be less than or equal to the other side" if inequality?
    if equilateral?
      :equilateral
    elsif scalene?
      :scalene
    elsif isosceles?
      :isosceles
    end
  end

  def equilateral?
    @side_1 == @side_2 && @side_1 == @side_3 && @side_2 == @side_3
  end

  def scalene?
    @side_1 != @side_2 && @side_1 != @side_3 && @side_2 != @side_3
  end

  def isosceles?
    @side_1 == @side_2 || @side_1 == @side_3 || @side_2 == @side_3
  end

  def illegal_size?
    (@side_1 <= 0) || (@side_2 <= 0) || (@side_3 <= 0)
  end

  def inequality?
    (@side_1 + @side_2 <= @side_3) || (@side_1 + @side_3 <= @side_2) || (@side_2 + @side_3 <= @side_1)
  end
end

class TriangleError < ArgumentError
end
