class TriangleError < StandardError; end

class Triangle
  def initialize(a, b, c)
    @sites = [a, b, c].sort
    raise TriangleError unless valid_sites?
  end

  def kind
    [:equilateral, :isosceles, :scalene].fetch(@sites.uniq.length - 1)
  end

  private

  def valid_sites?
    positive_sites? && touchable_sites?
  end

  def positive_sites?
    @sites.all? { |site| site > 0 }
  end

  def touchable_sites?
    @sites.first(2).reduce(:+) > @sites.last
  end
end
