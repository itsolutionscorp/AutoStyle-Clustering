class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).to_a.inject(:+) ** 2
  end

  def sum_of_squares
    (1..@num).map { |i| i ** 2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
