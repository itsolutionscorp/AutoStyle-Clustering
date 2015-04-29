class Squares

  def initialize(users_number)
    @range_of_users_number = (1..users_number)
  end

  def square_of_sums
    @squares ||= (@range_of_users_number.reduce(:+)) ** 2
  end

  def sum_of_squares
    @sums ||= @range_of_users_number.reduce { |result, num| result += num ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
