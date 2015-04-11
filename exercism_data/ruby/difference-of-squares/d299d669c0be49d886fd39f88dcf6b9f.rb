class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    result = 1.upto(@num).inject(:+)
    result * result
  end

  def sum_of_squares
    1.upto(@num).map { |e| e * e }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
