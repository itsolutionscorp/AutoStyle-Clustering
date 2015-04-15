class Squares
  def initialize first_n_natural_numbers
    @numbers = first_n_natural_numbers
  end

  def square_of_sums
    @sq_of_sums ||= (1..@numbers).reduce(:+)**2
  end

  def sum_of_squares
    @sums_of_sq ||= (1..@numbers).map {|n| n**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
