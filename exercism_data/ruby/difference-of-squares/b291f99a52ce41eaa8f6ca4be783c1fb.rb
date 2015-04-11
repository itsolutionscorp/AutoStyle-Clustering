class Squares
  def initialize first_n_natural_numbers
    @numbers = first_n_natural_numbers
  end

  def square_of_sums
    @sq_of_sums ||= (@numbers * (@numbers + 1) / 2) ** 2
  end

  def sum_of_squares
    @sums_of_sq ||= @numbers * (@numbers + 1) * (2 * @numbers + 1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
