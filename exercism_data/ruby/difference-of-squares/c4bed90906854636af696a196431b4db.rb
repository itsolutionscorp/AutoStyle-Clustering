class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    ret = 0
    for i in 1..@num
      ret += i**2
    end
    return ret
  end

  def square_of_sums
    ret = 0
    for i in 1..@num
      ret += i
    end
    return ret**2
  end

  def difference
    return square_of_sums - sum_of_squares
  end

end
