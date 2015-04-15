class Squares
  def initialize(last_number)
    @last_number = last_number
  end

  def square_of_sums
    1.upto(@last_number).inject(:+)**2
  end

  def sum_of_squares
    1.upto(@last_number).inject(0) { |sum, number| sum + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
