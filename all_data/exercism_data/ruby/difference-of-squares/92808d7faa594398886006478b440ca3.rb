class Squares
  def initialize(num)
    @num = num
  end
  def sum_of_squares
    (0..@num).to_a.inject {|arr, i| arr += i ** 2}
  end
  def square_of_sums
    (0..@num).to_a.inject {|arr, i| arr += i} ** 2
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
