class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (1..n).map { |x| x ** 2 }.reduce(:+)
  end

  def square_of_sums
    (1..n).reduce(:+) ** 2
  end
end
