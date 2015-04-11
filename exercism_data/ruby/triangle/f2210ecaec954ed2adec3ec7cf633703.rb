class Triangle
  def initialize (a, b, c)
    @l = [a, b, c]
    raise TriangleError if @l.map{|i| i <= 0}.any? or a + b + c <= 2 * @l.max
  end
  def kind
    case @l.uniq.size
    when 1 then :equilateral
    when 2 then :isosceles
    when 3 then :scalene
    end
  end
end
class TriangleError < ArgumentError
end
