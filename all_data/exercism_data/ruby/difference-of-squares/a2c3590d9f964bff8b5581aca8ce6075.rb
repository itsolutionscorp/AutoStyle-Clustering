class Squares
  attr_accessor :length

  def initialize(length)
    @length = length
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    square(sum)
  end

  def sum_of_squares 
    (1..length).to_a.inject do |result, num|
      result += square(num)
    end
  end

  def square(num)
    num ** 2
  end

  def sum
    (1..length).reduce(:+)
  end
end
