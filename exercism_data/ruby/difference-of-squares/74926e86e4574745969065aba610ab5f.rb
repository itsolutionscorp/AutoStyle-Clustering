class Squares

  attr_reader :length

  def initialize length
    @length = length.to_i
  end

  def square_of_sums
    (1..length).inject { |sum, n| sum + n } ** 2
  end

  def sum_of_squares
    (1..length).inject { |sum, n| sum + (n**2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
