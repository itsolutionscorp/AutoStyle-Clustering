class Squares
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def square_of_sums
    ((1..input).inject(:+))**2
  end

  def sum_of_squares
    (1..input).inject do |sum, n|
      sum + (n**2)
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
