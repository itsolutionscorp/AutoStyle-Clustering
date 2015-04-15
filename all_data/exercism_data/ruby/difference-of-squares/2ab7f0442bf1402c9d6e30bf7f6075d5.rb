class Squares
  attr_reader :numbers

  def initialize(number)
    @numbers = (1..number).to_a
  end

  def square_of_sums
    numbers.reduce(:+)**2
  end

  def sum_of_squares
    numbers.map{|n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
