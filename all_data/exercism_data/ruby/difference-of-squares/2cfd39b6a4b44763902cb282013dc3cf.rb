class Squares
  attr_reader :squares

  def initialize(squares)
    @squares = squares
  end

  def square_of_sums
    result = 0
    @squares.next.times do |num|
      result += num
    end
    result ** 2
  end

  def sum_of_squares
    result = 0
    @squares.times do |num|
      result += (num + 1) ** 2
    end
    result
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
