class TriangleError < StandardError
end

class Triangle
  attr_reader :sides, :s1, :s2, :s3


  def initialize(*sides)
    @s1, @s2, @s3 = sides
    @sides = sides
    validate_sides
  end

  def kind
    case
    when equilateral?
      :equilateral
    when isosceles?
      :isosceles
    when scalene?
      :scalene
    end
  end

  private

  def equilateral?
    sides.uniq.size == 1
  end

  def isosceles?
    sides.uniq.size == 2
  end

  def scalene?
    sides.uniq.size == 3
  end

  def validate_sides
    if @sides.any?{|side| side <= 0}
      raise TriangleError
    end

    @sides.permutation.each do |ss1,ss2,ss3|
      if ss1 + ss2 <= ss3
        raise TriangleError
      end
    end
  end

end
