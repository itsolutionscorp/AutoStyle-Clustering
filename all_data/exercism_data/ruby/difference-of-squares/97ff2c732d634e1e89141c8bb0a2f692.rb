class Squares < Struct.new(:n)
  def square_of_sums
    sum_of_numbers ** 2
  end

  def sum_of_squares
    n * (n + 1) * (2 * n + 1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end

protected

  def sum_of_numbers
    n * (n + 1) / 2
  end
end
