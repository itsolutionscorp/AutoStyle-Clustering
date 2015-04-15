class Squares
  def initialize(n)
    raise ArgumentError, 'n, the sole parameter, must be greater than zero.' unless n > 0
    @n = n
  end

  def square_of_sums
    result = 0
    for i in 1..@n
      result += i
    end
    return result**2
  end

  def sum_of_squares
    result = 0
    for i in 1..@n
      result += i**2
    end
    return result
  end

  def difference
    return self.square_of_sums - self.sum_of_squares
  end
end
