class Triangle
  def initialize(side1, side2, side3)
    @side1, @side2, @side3 = side1, side2, side3
  end
  
  def kind
    if invalid_triangle?
      raise TriangleError
    else
      triangle_type
    end
  end
  
  private
  
  def invalid_triangle?
    triangle_inequality_violation? || side_with_negative_length?
  end
  
  def triangle_inequality_violation?
    @side1 + @side2 <= @side3 || 
    @side2 + @side3 <= @side1 || 
    @side1 + @side3 <= @side2
  end
  
  def side_with_negative_length?
    @side1 < 0 || @side2 < 0 || @side3 < 0
  end
  
  def triangle_type
    if unique_side_lengths == 1
      :equilateral
    elsif unique_side_lengths == 2
      :isosceles
    elsif unique_side_lengths == 3
      :scalene
    end
  end
  
  def unique_side_lengths
    [@side1, @side2, @side3].uniq.length
  end
end

class TriangleError < StandardError
  
end
