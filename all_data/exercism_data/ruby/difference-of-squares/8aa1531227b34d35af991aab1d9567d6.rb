class Squares
  def initialize(first_n)
    @first_n = first_n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (1..@first_n).inject { |sum, n| sum + n**2 }
  end

  def square_of_sums
    (1..@first_n).inject(:+) ** 2
  end
end
