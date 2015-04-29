class Squares

  def initialize(input)
    @square_of_sums = 0
    @sum_of_squares = 0

    sum_result = 0

    (1..input).each do |number|
      @sum_of_squares += number**2
      sum_result += number
    end

    @square_of_sums = sum_result**2
  end

  def square_of_sums
    @square_of_sums
  end

  def sum_of_squares
    @sum_of_squares
  end

  def difference
    @square_of_sums - @sum_of_squares
  end
end
