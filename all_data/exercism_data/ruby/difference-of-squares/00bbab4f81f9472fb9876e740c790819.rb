class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    sums ** 2
  end

  def sum_of_squares(n = @n)
    return n if n <= 1
    n**2 + sum_of_squares(n-1)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sums(n = @n)
    return n if n <= 1
    n + sums(n-1)
  end

end
