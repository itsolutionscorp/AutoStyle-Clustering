class Squares

  def initialize(number)
    @number = number
  end

  attr_reader :number

  def square_of_sums
    (0..number).reduce(:+)**2
  end

  def sum_of_squares
    (0..number).inject { |sum, n| sum += n ** 2 }
  end

  def difference 
    square_of_sums - sum_of_squares
  end
end
