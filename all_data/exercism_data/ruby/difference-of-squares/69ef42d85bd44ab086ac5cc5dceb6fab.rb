class Squares
  def initialize(number)
    @sequence = [*(1..number)]
  end

  def square_of_sums
    @sequence.reduce(:+)**2
  end

  def sum_of_squares
    @sequence.map { |n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
