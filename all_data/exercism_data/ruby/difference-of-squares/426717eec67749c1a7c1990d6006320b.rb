class Squares

  def initialize numbers
    @numbers = numbers
  end

  def square_of_sums
    sum_of_numbers ** 2
  end

  def sum_of_squares
    range.inject(0) {|sum, num| sum += num ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum_of_numbers
    range.inject(&:+)
  end

  def range
    @range ||= Array(0..@numbers)
  end

end
