class Squares
  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    (1..@limit).inject { |sum, number| sum += number } ** 2
  end

  def sum_of_squares
    (1..@limit).inject { |sum, number| sum += number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
