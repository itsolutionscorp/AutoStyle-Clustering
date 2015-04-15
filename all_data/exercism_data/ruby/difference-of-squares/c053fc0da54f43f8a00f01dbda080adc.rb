class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    @num.times { |i| sum += (i + 1) }
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    @num.times { |i| sum += (i + 1) ** 2 }
    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
