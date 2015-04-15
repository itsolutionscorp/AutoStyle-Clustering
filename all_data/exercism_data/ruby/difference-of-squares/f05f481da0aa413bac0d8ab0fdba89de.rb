class Squares
  attr_reader :number

  def initialize(number)
    @number = (1..number).to_a
  end

  def square_of_sums
    number.reduce(:+)**2
  end

  def sum_of_squares
    number.reduce(0) do |sum, num|
      sum += num ** 2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
