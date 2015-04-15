class Squares
  attr_accessor :count

  def initialize(count)
    @count = count
  end

  def square_of_sums
    ((1..count).reduce(:+))**2
  end

  def sum_of_squares
    (1..count).map {|x| x**2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
