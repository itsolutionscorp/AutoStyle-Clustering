class Squares
  def initialize(first_n_numbers)
    @first_n_numbers = first_n_numbers
  end

  def square_of_sums
    (1..@first_n_numbers).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..@first_n_numbers).inject(0) { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
