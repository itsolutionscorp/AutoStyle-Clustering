class Squares

  def initialize(users_number)
    @range_of_users_number = (1..users_number)
  end

  def square_of_sums
    @squares ||= square(@range_of_users_number.reduce(:+))
  end

  def sum_of_squares
    @sums ||= @range_of_users_number.reduce { |result, num| result += square(num) }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def square(number)
    number ** 2
  end
end
