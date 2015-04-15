class Squares
  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def square_of_sums
    @square_sums ||= (1..@upper_bound).reduce(&:+)**2
  end

  def sum_of_squares
    @sum_squares ||= (1..@upper_bound).inject(0) { |sum, i| sum + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
