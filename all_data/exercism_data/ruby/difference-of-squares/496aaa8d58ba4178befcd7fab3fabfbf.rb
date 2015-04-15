class Squares

  def initialize(high_number)
    @high_number = high_number
  end

  def square_of_sums
    sums = (1..@high_number).inject(:+)
    sums ** 2
  end

  def sum_of_squares
    (1..@high_number).inject { |sum, n| sum + n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
