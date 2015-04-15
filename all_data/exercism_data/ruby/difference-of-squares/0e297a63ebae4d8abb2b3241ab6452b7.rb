class Squares

  def initialize(number)
    @number    = number
    @sq_of_sum = square_of_sums
    @sum_of_sq = sum_of_squares
  end

  def square_of_sums
    sum = 0
    for i in 1..@number
      sum += i
    end
    @sq_of_sum = sum**2
  end

  def sum_of_squares
    sum_of_sq = 0
    for i in 1..@number
      sum_of_sq += i**2
    end
    @sum_of_sq = sum_of_sq
  end

  def difference
    @sq_of_sum - @sum_of_sq
  end

end
