class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    sum = 0
    for i in 1..@num
      sum += i**2
    end
    sum
  end

  def square_of_sums
    sum = 0
    for i in 1..@num
      sum += i
    end
    sum ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
