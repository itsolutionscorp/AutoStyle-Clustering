class Squares
  def initialize n
    @n = n
  end

  attr_reader :n

  def square_of_sums
    (n * (n + 1) / 2) ** 2
  end

  def sum_of_squares
    (1..n).map(&:**2).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
