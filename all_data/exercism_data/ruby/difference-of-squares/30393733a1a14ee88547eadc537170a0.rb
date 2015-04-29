class Squares
  attr_accessor :max
  def initialize(max)
    @max = max
  end

  def square_of_sums
    sum = 0
    (1..max).each{|i| sum += i}
    sum**2
  end

  def sum_of_squares
    sum = 0
    (1..max).each{|i| sum += i**2}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
