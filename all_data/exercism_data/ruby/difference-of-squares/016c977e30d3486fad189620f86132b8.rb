class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    @num.downto(1).reduce(:+) ** 2
  end

  def sum_of_squares
    @num.downto(1).reduce(0) { |sum, n| sum + (n**2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
