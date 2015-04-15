class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    for x in 1..@num do
        sum += x
     end
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    for x in 1..@num do
      x = x ** 2
      sum += x
    end
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
