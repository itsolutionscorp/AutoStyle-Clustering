class Squares
  def initialize(i)
    @numbers = (1..i).to_a
  end

  def sum_of_squares
    @numbers.map { |n| n ** 2 }.reduce(&:+)
  end

  def square_of_sums
    @numbers.reduce(&:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
