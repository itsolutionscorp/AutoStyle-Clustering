class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    sum = 0
    for i in 1..@num do
      sum += i ** 2
    end
    return sum
  end

  def square_of_sums
    sum = 0
    for i in 1..@num do
      sum += i
    end
    square = sum ** 2
    return square
  end

  def difference
    diff = square_of_sums - sum_of_squares
    return diff
  end

end
