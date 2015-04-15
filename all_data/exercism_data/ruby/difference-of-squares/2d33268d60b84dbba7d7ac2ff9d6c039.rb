class Squares
  @num = 0
  def initialize(num)
    @num = num
  end

  def sum_of_squares
    (1..@num).reduce(0) {|sum, cur| sum + cur ** 2}
  end

  def square_of_sums
    (1..@num).reduce(:+) ** 2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
