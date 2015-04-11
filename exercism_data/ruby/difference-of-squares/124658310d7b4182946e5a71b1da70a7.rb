class Squares
  def initialize(number)
    @number = number
    @temp = number*(number + 1) >> 1
  end

  def square_of_sums
    @square_of_sums ||= @temp * @temp
  end

  def sum_of_squares
    @sum_of_squares ||= @temp * ((@number << 1) + 1) / 3
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
