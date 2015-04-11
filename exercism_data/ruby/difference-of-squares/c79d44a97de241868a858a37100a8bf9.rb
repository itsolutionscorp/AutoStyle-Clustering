class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    for i in 1..@num
      sum += i
    end
    return sum**2
  end

  def sum_of_squares
    sum = 0
    for i in 1..@num
      sum += i**2
    end
    return sum
  end

  def difference
    return (sum_of_squares - square_of_sums).abs
  end

end
