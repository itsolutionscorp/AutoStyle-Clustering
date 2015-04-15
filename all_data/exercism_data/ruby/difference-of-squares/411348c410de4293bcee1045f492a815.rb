class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    (Array(1..number)).reduce(:+) ** 2
  end

  def sum_of_squares
    ((1..number).map { |i| i*i }).reduce(:+)
  end

  def difference
    sum_of_squares > square_of_sums ? sum_of_squares - square_of_sums : square_of_sums - sum_of_squares
  end

end
