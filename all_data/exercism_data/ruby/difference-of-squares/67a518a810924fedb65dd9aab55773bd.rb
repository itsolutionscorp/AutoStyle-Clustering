class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    n = 0
    for i in 1..@num do
      n += i
    end

    n**2
  end

  def sum_of_squares
    n = 0
    for i in 1..@num do
      n += i**2
    end

    n
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end
end
