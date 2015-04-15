class Squares

  def initialize(input)
    @seed_value = input
  end

  def sum_of_squares
    (1..@seed_value).inject { |result, value| result += value**2 }
  end

  def square_of_sums
    (1..@seed_value).reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
