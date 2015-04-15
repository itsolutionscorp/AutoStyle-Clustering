class Squares
  attr_reader :numbers

  def initialize(n)
    @numbers = (1..n)
  end

  def square_of_sums
    @square_of_sums ||= numbers.inject(:+)**2
  end

  def sum_of_squares
    @sum_of_squares ||= numbers.map {|n| n**2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
