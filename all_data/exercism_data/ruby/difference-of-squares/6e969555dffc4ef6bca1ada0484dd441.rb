class Squares
  def initialize(number_n)
    @number_n = number_n
  end

  def square_of_sums
    first_n_natural_numbers.inject(:+) ** 2
  end

  def sum_of_squares
    first_n_natural_numbers.inject(0) { |sum, num| sum + (num ** 2) }
  end

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  private

  def first_n_natural_numbers
    1.upto(@number_n)
  end
end
