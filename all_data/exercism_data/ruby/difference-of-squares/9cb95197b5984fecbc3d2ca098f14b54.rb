class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).inject(:+) ** 2
  end

  def sum_of_squares
    (1..@num).inject { |s, i| s += i ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
