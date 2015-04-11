class Squares
  attr_reader :numbers

  def initialize(number)
    @numbers = (1..number).to_a
  end

  def square_of_sums
    numbers.reduce(:+)**2
  end

  def sum_of_squares
    numbers.reduce { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
