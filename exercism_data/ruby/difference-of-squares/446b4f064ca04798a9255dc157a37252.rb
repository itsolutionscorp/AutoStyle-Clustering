class Squares
  attr_reader :range
  def initialize(number)
    @range = (1..number).to_a
  end

  def square_of_sums
    range.reduce(:+).square
  end

  def sum_of_squares
    range.map(&:square).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end

class Fixnum
  def square
    self ** 2
  end
end
