class Squares
  attr_reader :num
  def initialize(num)
    @num = num + 1
  end

  def square_of_sums
    sum = 0
    @num.times { |counter| sum += counter }
    sum**2
  end

  def sum_of_squares
    sum = 0
    @num.times { |counter| sum += counter**2 }
    sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
