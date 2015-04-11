class Triangle
  attr_reader :a, :b, :c, :sides

  def initialize(a, b, c)
    @a, @b, @c = a, b, c
    @sides = [@a, @b, @c]
  end

  def kind
    raise TriangleError, "Whoops!" unless valid?
    triangle_type_by_unique_sides[sides.uniq.count]
  end



  private

  def valid?
    sides_have_positive_values? && sides_have_triangle_inequality?  
  end

  def sides_have_positive_values?
    sides.all? { |s| s > 0 }
  end

  def sides_have_triangle_inequality?
    sorted = sides.sort
    sorted[0] + sorted[1] > sorted[2]
  end

  def triangle_type_by_unique_sides
    { 1 => :equilateral, 2 => :isosceles, 3 => :scalene }
  end

end

class TriangleError < ArgumentError; end
