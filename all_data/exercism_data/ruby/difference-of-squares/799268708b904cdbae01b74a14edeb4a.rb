class Squares
  def initialize(num)
    @num = num
  end
  def difference
    (square_of_sums - sum_of_squares).abs
  end
  def square_of_sums
    (1..@num).inject(:+) ** 2
  end
  def sum_of_squares
    (1..@num).map {|i| i ** 2 }.inject(:+)
  end
end
