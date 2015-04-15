class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    value = summer(@num)
    value ** 2
  end

  def sum_of_squares
    return @num if @num == 1
    value = @num**2
    @num -= 1
    value + sum_of_squares
  end

  def difference
    self.square_of_sums - sum_of_squares
  end

  def summer(num)
    return 1 if num == 1
    num + summer(num - 1)
  end

end
