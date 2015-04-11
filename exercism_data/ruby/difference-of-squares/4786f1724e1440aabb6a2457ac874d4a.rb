class Squares
  def initialize n
    @n = n
  end

  def range
    (1..@n)
  end

  def square_of_sums
    range.reduce(:+) ** 2
  end

  def sum_of_squares
    range.reduce(0) { |acc, n| acc + (n ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
