class Squares
  attr_reader :n

  def initialize n
    @n = 1..n
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  def square_of_sums
    n.reduce(:+)**2
  end

  def sum_of_squares
    n.reduce(0) { |a, e| a + e**2 }
  end
end
