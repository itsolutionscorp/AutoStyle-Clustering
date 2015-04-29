class Squares
  def initialize(x)
    @num = x
  end

  def sum_of_squares
    1.upto(@num).map { |x| x**2 }.reduce(:+)
  end

  def square_of_sums
    1.upto(@num).reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
