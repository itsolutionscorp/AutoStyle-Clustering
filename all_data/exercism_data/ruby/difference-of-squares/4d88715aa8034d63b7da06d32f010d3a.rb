class Squares

  def initialize(max)
    @max = max
  end

  def square_of_sums
    sum = 0
    for i in 1..@max
      sum += i
    end
    return sum**2
  end

  def sum_of_squares
    sum = 0
    for i in 1..@max
      sum += i**2
    end
    return sum
  end

  def difference
    return self.square_of_sums - self.sum_of_squares
  end

end
