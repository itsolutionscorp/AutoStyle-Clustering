class Squares

  def initialize value
    @value = value
  end

  def sum_of_squares
    @sum_of_squares ||= 2.upto(@value).inject(1) { |sum,n| sum + n**2 }
  end

  def square_of_sums
    @square_of_sums ||= 1.upto(@value).inject(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
