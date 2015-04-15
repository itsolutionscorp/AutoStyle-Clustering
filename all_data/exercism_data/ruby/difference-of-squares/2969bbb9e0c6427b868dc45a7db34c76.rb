class Squares < Struct.new(:n)
  def square_of_sums
    (1..n).reduce(0) { |sum, i| sum + i } ** 2
  end

  def sum_of_squares
    (1..n).reduce(0) { |sum, i| sum + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
