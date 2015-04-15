class Squares

  def initialize(users_number)
    @numbers = (1..users_number)
  end

  def square_of_sums
    @squares ||= (@numbers.reduce(:+)) ** 2
  end

  def sum_of_squares
    @sums ||= @numbers.reduce { |result, num| result + num ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
