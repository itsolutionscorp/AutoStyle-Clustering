class Squares
  attr_reader :max

  def initialize(number)
    @max = number
  end

  def square_of_sums
    1.upto(max).reduce(:+)**2
  end

  def sum_of_squares
    1.upto(max).reduce(0) do |acc, num|
      acc + num**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
