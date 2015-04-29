class Grains
  def initialize
    @squares = (0..63).map { |n| 2**n }
    @total   = @squares.reduce(:+)
  end

  def square(n)
    @squares[n-1]
  end

  def total
    @total
  end
end
