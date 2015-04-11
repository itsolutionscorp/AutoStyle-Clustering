class Triangle
  TRIANGLE_TYPES = {
      0 => :scalene,
      1 => :isosceles,
      2 => :equilateral
  }

  def initialize(*args)
    @sides = args.to_a.sort
  end

  def kind
    raise TriangleError if illegal
    tally = 0
    tally +=1 if @sides[0] == @sides[1]
    tally +=1 if @sides[0] == @sides[2] || @sides[1] == @sides[2]

    TRIANGLE_TYPES[tally]

  end

  private

  def illegal
    ((@sides[0] <=0) || (@sides[1] <=0) || (@sides[2] <=0))  || (@sides[2] >= (@sides[0] + @sides[1]))
  end

end

class TriangleError < Exception

end
