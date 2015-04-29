class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).reduce(:+)**2
  end

  def sum_of_squares
    (1..@num).map { |n| n**2 }.reduce(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
