class Triangle
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
    fail TriangleError unless valid?
  end

  def kind
    return :equilateral if a == b && b == c
    return :isosceles   if a == b || b == c || c == a
    :scalene
  end

  private

  def valid?
    sides_have_length? && sides_satisy_equality_rule?
  end

  def sides_have_length?
    a > 0 && b > 0 && c > 0
  end

  def sides_satisy_equality_rule?
    a < b + c && b < c + a && c < a + b
  end

  attr_reader :a, :b, :c
end

TriangleError = Class.new(StandardError)
