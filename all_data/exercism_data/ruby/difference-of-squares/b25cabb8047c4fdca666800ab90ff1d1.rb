class Squares
  attr_reader :terms

  def initialize(n)
    @terms = 1..n
  end

  def sum_of_squares
    terms.map {|x| x**2 }.inject(&:+)
  end

  def square_of_sums
    terms.inject(&:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
