Squares = Struct.new(:n) do
  def square_of_sums
    1.upto(n).inject(:+)**2
  end

  def sum_of_squares
    1.upto(n).inject(0) { |sum, i| sum + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
