class Triangle

  attr_reader :side_x, :side_y, :side_z

  def initialize side_x, side_y, side_z
    @side_x = side_x
    @side_y = side_y
    @side_z = side_z
  end

  def kind 
    raise TriangleError if not is_valid?

    if equilater?
      :equilateral
    elsif scalene?
      :scalene
    else
      :isosceles
    end
  end

private

  def is_valid?
    side_x + side_y > side_z and side_y + side_z > side_x and side_z + side_x > side_y 
  end
  
  def equilater?
    side_x == side_y and side_x == side_z and side_y == side_z  
  end

  def scalene?
    side_x != side_y and side_x != side_z and side_y != side_z
  end

end

class TriangleError < ArgumentError
end
