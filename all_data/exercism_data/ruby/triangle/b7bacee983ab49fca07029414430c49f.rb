class Triangle
  TRIANGLE_TYPES = {
      1 => :equilateral,
      2 => :isosceles,
      3 => :scalene
  }

  def initialize(*args)
    @sides = args.to_a.sort
    raise TriangleError if illegal
  end

  def kind
    TRIANGLE_TYPES[@sides.uniq.count]
  end

  private

  def illegal
    ((@sides[0] <=0) || (@sides[1] <=0) || (@sides[2] <=0))  || (@sides[2] >= (@sides[0] + @sides[1]))
  end

end

class TriangleError < Exception

end
