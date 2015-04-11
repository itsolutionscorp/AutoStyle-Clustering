class Squares
  
  def initialize(num)
    @num = num > 0 ? num : -num
  end

  def square_of_sums
    sums = (@num * (@num + 1))/ 2
    sums * sums
  end

  def sum_of_squares
    sum = (@num * (@num + 1) * (2 * @num + 1)) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
