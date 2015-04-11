class Triangle
  def initialize(*sides)
    raise TriangleError unless sides.count == 3
    @small, @medium, @large = *sides.sort
  end

  def kind
    if invalid? 
      raise TriangleError
    elsif equilateral?
      :equilateral
    elsif isoceles?
      :isosceles
    else
      :scalene
    end
  end

  private

    def invalid?
      @small < 0 || @small + @medium <= @large
    end
    
    def equilateral?
      @small == @medium && @medium == @large
    end

    def isoceles?
      @small == @medium || @medium == @large
    end
end

class TriangleError < ArgumentError; end
