class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    square(sums)
  end

  def sum_of_squares
    first_n_numbers.reduce {|sum, x| sum + square(x)}
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def square(x)
    x * x
  end

  def sums
    first_n_numbers.reduce(:+)
  end

  def first_n_numbers
    (1..@n)
  end

end
