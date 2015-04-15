class TriangleError < RuntimeError  
end

class Triangle
  
  def initialize(a, b, c)
    @sides = [ a, b, c ]
    sum = 0
    @sides.each { |a| sum+=a }
    @sides.each do |s|
      if s <= 0 || (s*2) >= sum
        raise TriangleError
      end
    end
  end

  def kind
    case @sides.uniq.length
    when 1
      :equilateral
    when 2
      :isosceles
    else
      :scalene
    end
  end

end
