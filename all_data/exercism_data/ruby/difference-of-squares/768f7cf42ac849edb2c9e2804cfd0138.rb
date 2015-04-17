Squares = Struct.new(:limit) do
  def square_of_sums
    1.upto(limit).reduce(:+) ** 2
  end

  def sum_of_squares
    1.upto(limit).reduce { |sum, i| sum + i ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end