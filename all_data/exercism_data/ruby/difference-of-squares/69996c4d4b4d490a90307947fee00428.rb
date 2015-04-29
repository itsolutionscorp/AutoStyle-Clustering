class Squares

  def initialize(number)
    @number = number
    @sq_sum = 0
  end

  def square_of_sums
    @sq_sum = ((1..@number).inject { |sum, v| sum + v  })**2
    @sq_sum
  end

  def sum_of_squares
    @sum_sq = (1..@number).inject { |sum, v| sum + v**2  }
    @sum_sq
  end

  def difference
    @difference_between = square_of_sums - sum_of_squares
    @difference_between
  end

end
