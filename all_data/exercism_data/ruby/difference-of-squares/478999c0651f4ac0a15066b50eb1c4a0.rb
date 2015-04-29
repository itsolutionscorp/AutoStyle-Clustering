class Squares
  attr_accessor :num, :sqr

  def initialize(num)
    @num = num
    @sqr = lambda { |val| val * val }
  end

  def sum_of_squares
    (1..@num).to_a.map(&sqr).reduce(&:+)
  end

  def square_of_sums
    sqr.call((1..@num).to_a.reduce(&:+))
  end

  def difference
    square_of_sums - sum_of_squares
  end


end
