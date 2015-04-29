class Squares

  def initialize(number)
    @rounds = (1..number)
  end

  def square_of_sums
    sum = @rounds.inject(0, &:+)
    result = sum**2
  end

  def sum_of_squares
    result = @rounds.inject(0) do |sum, number|
      sum + number**2
    end
    result
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
