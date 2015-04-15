class Squares
  def initialize(number_to)
    @number_to = number_to
  end

  def square_of_sums
    @square_of_sums ||= square(numbers.reduce(:+))
  end

  def sum_of_squares
    @sum_of_squares ||= numbers.map(&method(:square)).reduce(:+)
  end

  def difference
    @difference ||= square_of_sums - sum_of_squares
  end

  private

  attr_reader :number_to

  def numbers
    @numbers ||= (1..number_to)
  end

  def square(x)
    x**2
  end
end
