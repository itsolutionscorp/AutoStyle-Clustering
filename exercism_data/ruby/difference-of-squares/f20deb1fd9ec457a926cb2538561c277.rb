class Squares < Struct.new(:number)
  def square_of_sums
    sum_of_numbers ** 2
  end

  def sum_of_squares
    number * (number + 1) * (2 * number + 1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end

protected

  def sum_of_numbers
    number * (number + 1) / 2
  end
end
