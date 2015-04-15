class Squares
  attr_reader :n, :range

  def initialize(n)
    @n     = n
    @range = (1..n)
  end

  def sum_of_squares
    range.map { |k| k ** 2 }.reduce(:+)
  end

  def square_of_sums
    range.reduce(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
