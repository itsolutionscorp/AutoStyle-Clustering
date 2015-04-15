class Squares < Struct.new(:n)
  def square_of_sums
    n.times.reduce(0) { |sum, i| sum + i+1 } ** 2
  end

  def sum_of_squares
    n.times.reduce(0) { |sum, i| sum + (i+1)**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
