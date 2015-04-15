class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    1.upto(@num).reduce(:+) ** 2
  end

  def sum_of_squares
    1.upto(@num).inject { |sum, n| sum + n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
