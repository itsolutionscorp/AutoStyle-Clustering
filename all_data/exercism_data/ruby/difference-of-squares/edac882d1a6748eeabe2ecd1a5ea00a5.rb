class Squares < Struct.new(:limit)
  def square_of_sums
    1.upto(limit).inject(:+) ** 2
  end

  def sum_of_squares
    1.upto(limit).map { |i| i * i }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
