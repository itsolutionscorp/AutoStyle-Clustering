class Grains
  def initialize(size=64)
    @size = size
  end

  def square(n)
    2**(n-1)
  end

  def total
    (1..@size).inject { |sum, n| sum + square(n) }
  end
end
