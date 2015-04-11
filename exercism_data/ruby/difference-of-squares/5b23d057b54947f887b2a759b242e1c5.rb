module Enumerable
  def sum
    self.reduce(:+)
  end

  def squares
    self.map {|n| n**2}
  end
end

class Squares
  def initialize(max)
    @sequence = (1..max)
  end

  def square_of_sums
    @sequence.sum**2
  end

  def sum_of_squares
    @sequence.squares.sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
