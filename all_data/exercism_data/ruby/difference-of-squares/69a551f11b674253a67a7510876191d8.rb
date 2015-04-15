class Squares
  attr_reader :limit

  def initialize(limit)
    @limit = limit
  end

  def square_of_sums
    sums**2
  end

  def sum_of_squares
    squares.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  def sums
    @sums ||= range.reduce(&:+)
  end

  def squares
    @squares ||= range.map{|i| i**2}
  end

  def range
    @range ||= 0.upto(limit)
  end
end
