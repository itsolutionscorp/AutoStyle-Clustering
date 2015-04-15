class Squares

  def initialize(n)
    @n = 1..n
  end

  def square_of_sums
    @n.reduce(:+).abs2
  end

  def sum_of_squares
    @n.map(&:abs2).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
