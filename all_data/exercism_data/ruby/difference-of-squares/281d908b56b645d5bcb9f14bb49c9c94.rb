class Squares
  attr_accessor :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = 0
    (1..num).each {|number| sum += number }
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    (1..num).each {|number| sum += number ** 2}
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
