class Squares
  def initialize(num)
    @num = num
  end
  attr_accessor :num

  def square_of_sums
    (1..num).collect.inject(:+) ** 2
  end

  def sum_of_squares
    (1..num).collect{|x| x**2}.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
