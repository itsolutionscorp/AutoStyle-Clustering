class Grains
  def initialize
    @squares = [0, 1]
  end

  def square(n)
    @squares[n] = 2 * square(n - 1) unless @squares[n]
    @squares[n]
  end

  def total
    (1..64).reduce(0) { |a, e| a + square(e) }
  end
end
