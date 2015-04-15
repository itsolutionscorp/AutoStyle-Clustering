class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    1.upto(@number).reduce(&:+)**2
  end

  def sum_of_squares
    1.upto(@number).map { |n| n**2 }.reduce(&:+) # => 385
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
