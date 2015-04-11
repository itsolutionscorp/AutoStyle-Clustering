class Squares
  attr_reader :number_of_squares

  def initialize(number_of_squares)
    @number_of_squares = number_of_squares
  end

  def sum_of_squares
    (1..number_of_squares).inject(0) do |memo, i|
      memo + (i ** 2)
    end
  end

  def square_of_sums
    (1..number_of_squares).inject(&:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
