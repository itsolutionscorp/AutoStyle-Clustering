class Squares
  def initialize (num)
    @num = num
  end

  def square_of_sums
    counter = 0
    @num.times.reduce(0) { |sum|
      counter += 1
      sum + counter
    } ** 2
  end

  def sum_of_squares
    counter = 0
    @num.times.reduce(0) { |sum|
      counter += 1
      sum + counter ** 2
    }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
