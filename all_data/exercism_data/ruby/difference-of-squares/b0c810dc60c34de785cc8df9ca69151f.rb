class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  def square_of_sums
    1.upto(n).reduce(0) { |a, e| a += e; a }**2
  end

  def sum_of_squares
    1.upto(n).reduce(0) { |a, e| a += (e**2); a }
  end
end
