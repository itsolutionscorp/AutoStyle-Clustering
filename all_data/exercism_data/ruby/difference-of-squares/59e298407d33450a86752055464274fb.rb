class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    (1..@n).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..@n).reduce { |sum, x| sum + x ** 2 }
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

end
