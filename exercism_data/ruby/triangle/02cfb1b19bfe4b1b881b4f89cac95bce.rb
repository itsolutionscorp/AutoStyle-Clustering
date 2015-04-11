class TriangleError < StandardError; end

class Triangle
  attr_reader :a, :b, :c

  def initialize(a,b,c)
    @a = a 
    @b = b
    @c = c
  end

  def kind
    raise TriangleError if impossible?
    if equilateral?
      :equilateral
    elsif isosceles?
      :isosceles
    else
      :scalene
    end
  end

  private

  def sides
    @sides ||= [a, b, c]
  end

  def equilateral?
    sides.uniq.size == 1
  end

  def isosceles?
    sides.uniq.size == 2
  end

  def impossible_side_length
    sides.any? {|side| side <= 0}
  end

  def violates_inequality
    a + b <= c || a + c  <= b || c + b <= a
  end

  def impossible?
    impossible_side_length || violates_inequality
  end
  
end
