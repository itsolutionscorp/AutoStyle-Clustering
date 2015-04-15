class Squares
  def initialize num
    @num = num
  end

  def square_of_sums
    (0..@num).inject(:+) ** 2
  end

  def sum_of_squares
    (0..@num).inject { |sum, num| sum += num*num }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
