class Squares

  def initialize(input)
    @limit = input
  end

  def square_of_sums
    range.reduce(&:+) ** 2
  end

  def sum_of_squares
    range.map{|n| n**2}.reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def range
    @range ||= 0.upto(@limit)
  end

end
