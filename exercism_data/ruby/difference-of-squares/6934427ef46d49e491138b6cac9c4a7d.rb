class Squares
  attr_accessor :integer

  def initialize(integer)
    @integer = integer
  end

  def sum_of_squares
    (1..@integer).map{ |n| n**2}.reduce(:+)
  end

  def square_of_sums
    (1..@integer).reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
