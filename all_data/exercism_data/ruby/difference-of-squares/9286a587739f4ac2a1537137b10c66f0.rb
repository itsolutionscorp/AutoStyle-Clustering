Squares = Struct.new(:limit) do
  def square_of_sums
    1.upto(limit).reduce(:+) ** 2
  end

  def sum_of_squares
    1.upto(limit).map { |i| i ** 2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
