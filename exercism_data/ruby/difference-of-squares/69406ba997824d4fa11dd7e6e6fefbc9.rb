class Squares
  attr_accessor :range

  def initialize(num)
    @range = 1..num
  end

  def sum_of_squares
    range.reduce{ |acc, v| acc + (v ** 2) }
  end

  def square_of_sums
    range.reduce(&:+).abs2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
