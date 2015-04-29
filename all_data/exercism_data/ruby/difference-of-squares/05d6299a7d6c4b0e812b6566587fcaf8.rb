class Squares


  def initialize(number)
    @number = number
  end

  attr_reader :number

  def square_of_sums
    (1..number).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..number).map { |number| number**2}.reduce(0, :+)

  end

  def difference
    square_of_sums - sum_of_squares
  end

end
