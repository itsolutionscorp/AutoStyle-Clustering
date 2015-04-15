class Squares
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..num).reduce(:+)**2
  end

  def sum_of_squares
    (1..num).collect { |n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
