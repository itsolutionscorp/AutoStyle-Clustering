class Triangle
  attr_reader :kind

  def initialize(*args)
    @side_a = args.sort[0]
    @side_b = args.sort[1]
    @side_c = args.sort[2]
  end

  def kind
    raise TriangleError if @side_a + @side_b <= @side_c
    return :equilateral if @side_a == @side_b && @side_a == @side_c
    return :isosceles   if @side_b == @side_c
    return :scalene
  end
end

class TriangleError < StandardError; end
