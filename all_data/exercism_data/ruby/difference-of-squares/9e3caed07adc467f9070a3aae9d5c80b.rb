class Squares
  attr_accessor :number
  def initialize(num)
    @number = num.to_i
  end

  def square_of_sums
    (1..number).reduce(&:+)**2
  end

  def sum_of_squares
    (1..number).map { |n| n**2 }.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
