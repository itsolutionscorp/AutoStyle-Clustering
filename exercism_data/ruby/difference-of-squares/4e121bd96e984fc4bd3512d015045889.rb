class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    value = self.summer(@num)
    return value ** 2
  end

  def sum_of_squares
    if @num == 1
      return 1
    else
      value = @num**2
      @num -= 1
      return value + self.sum_of_squares 
    end
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

  def summer(num)
    if num == 1
      return 1
    else
      return num + summer(num - 1)
    end
  end

end
