class Squares
  def initialize(n)
    @n = (1..n)
  end

  def square_of_sums
    @n.reduce(:+)**2
  end

  def sum_of_squares
    @n.map { |n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
