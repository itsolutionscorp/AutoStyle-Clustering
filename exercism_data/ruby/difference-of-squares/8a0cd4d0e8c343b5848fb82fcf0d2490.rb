class Squares
  attr_accessor :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    return (1..self.num).reduce(:+) ** 2
  end

  def sum_of_squares
    return ((1..self.num).map {|n| n ** 2}).reduce(:+)
  end

  def difference
    return (square_of_sums - sum_of_squares).abs
  end

end
