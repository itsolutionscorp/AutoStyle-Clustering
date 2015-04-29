class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).to_a.reduce(:+) ** 2
  end

  def sum_of_squares
    squares = []
    (1..@num).each {|n| squares << n ** 2 }
    squares.reduce(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
