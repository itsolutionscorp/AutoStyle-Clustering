class Squares
  attr_reader :limit

  def initialize limit
    @limit = limit
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    1.upto(limit).map{ |x| x ** 2 }.reduce(:+)
  end

  def square_of_sums
    sum = 1.upto(limit).reduce(:+)
    sum ** 2
  end
end
