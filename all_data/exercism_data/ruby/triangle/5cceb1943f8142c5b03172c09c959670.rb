class TriangleError < ArgumentError
end

class Triangle
  def initialize(a, b, c)
    @short, @medium, @long = [a, b, c].sort
  end

  def kind
    guard_against_illegal_triangle

    if @short == @long
      :equilateral
    elsif @short == @medium || @medium == @long
      :isosceles
    else
      :scalene
    end
  end

  private

  def guard_against_illegal_triangle
    raise TriangleError, 'Sides should be positive' unless @short > 0
    raise TriangleError, 'Inequality violated' unless @short + @medium > @long
  end
end
