class Squares

  def initialize(n)
    if n < 0 || n % 1 != 0
      raise ArgumentError, "\`#{n}\' is not natural number."
    end
    @numbers = 0..n
  end

  def square_of_sums
    @numbers.reduce(:+) ** 2
  end

  def sum_of_squares
    @numbers.reduce { |sum, x| sum + (x ** 2) }
  end

  def difference
    square_of_sums() - sum_of_squares()
  end

end
