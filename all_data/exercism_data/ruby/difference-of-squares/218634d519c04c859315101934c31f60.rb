class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    sums ** 2
  end

  def sum_of_squares(n = @n)
    squares.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sums
    (1..@n).inject(:+)
  end

  def squares
    (1..@n).map { |x| x**2 }
  end

end
