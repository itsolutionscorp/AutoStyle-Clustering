class Squares
  attr_writer :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).reduce(:+)**2
  end

  def sum_of_squares
    (1..@num).reduce { |sum, n| sum + n**2 }
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
