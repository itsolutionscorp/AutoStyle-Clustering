class Grains
  def initialize
    @squares = (1..64).map { |x| 2**(x-1) }
  end

  def square(n)
    @squares[n - 1]
  end

  def total
    @squares.inject(:+)
  end
end
