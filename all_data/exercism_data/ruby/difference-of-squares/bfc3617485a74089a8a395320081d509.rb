class Squares
  attr_reader :number

  def initialize(number)
    @number = (1..number)
  end
  
  def sum_of_squares
    number.reduce(0) { |x, y| x + y**2}
  end

  def square_of_sums
    number.reduce(0, :+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
