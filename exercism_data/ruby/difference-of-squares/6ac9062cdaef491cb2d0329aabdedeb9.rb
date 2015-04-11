class Squares

  IDENTITY = lambda {|x| x }
  SQUARE  = lambda {|x| x * x }

  def initialize(number)
    @number = number
  end

  def square_of_sums
    @square_of_sums ||= SQUARE.call(sum_of_number)
  end

  def sum_of_squares
    @sum_of_squares ||= sum_of_values(@number, SQUARE)
  end

  def difference
    @difference ||= square_of_sums - sum_of_squares
  end

  private

  def sum_of_number
    @sum_of_number ||= sum_of_values(@number, IDENTITY)
  end

  def sum_of_values(i, transform, result = 1)
    i == 1 ? result : sum_of_values(i - 1, transform, result + transform.call(i))
  end
end
