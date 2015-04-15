class Squares
  def initialize number
    @number = number
  end

  def square_of_sums
    @square_of_sums ||= range.inject(:+) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= range.map { |x| x ** 2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def range
    (1 .. @number)
  end

end
