class Squares

  def initialize(number)
    @rounds = (1..number)
  end

  def square_of_sums
    @rounds.inject(0, &:+)**2
  end

  def sum_of_squares
    @rounds.inject(0) { |sum, number| sum + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
