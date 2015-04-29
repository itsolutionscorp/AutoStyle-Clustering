class Squares
  def initialize(input)
    @input = input
  end

  def square_of_sums
    value = 0
    (1..@input).each { |index| value += index }
    value**2
  end

  def sum_of_squares
    value = 0
    (1..@input).each { |index| value += index**2 }
    value
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
