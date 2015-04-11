class Squares
  def initialize(upper_bound)
    @n = upper_bound
  end

  # See https://oeis.org/A000217
  def square_of_sums
    @square_of_sums ||= (@n * (@n + 1) / 2)**2
  end

  # See https://oeis.org/A000330
  def sum_of_squares
    @sum_of_squares ||= @n * (@n+1) * (2*@n+1) / 6
  end

  # See https://oeis.org/A052149
  def difference
    @difference ||= @n * (@n-1) * (@n+1) * (3*@n+2) / 12
  end
end
