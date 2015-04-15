class Squares

  attr_reader :number

  def initialize number
    @number = number
  end

  def square_of_sums
    iterate_numbers { |sum, item| sum += item  } ** 2
  end

  def sum_of_squares
    iterate_numbers { |sum, item| sum += ( item ** 2 ) }
  end

  def difference
    square_of_sums - sum_of_squares
  end

private

  def iterate_numbers &block
    (1..number).inject 0, &block
  end

end
