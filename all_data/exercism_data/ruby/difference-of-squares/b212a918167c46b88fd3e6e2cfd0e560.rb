class Squares
  attr_reader :numbers

  def initialize(numbers)
    @numbers = (1..numbers)
  end
  
  def sum_of_squares
    numbers.reduce(0) { |x, y| x + y**2}
  end

  def square_of_sums
    numbers.reduce(0, :+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
