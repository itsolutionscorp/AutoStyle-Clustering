class Squares
  attr_reader :sum_of_squares, :square_of_sums
  def initialize(n)
    @n = n

    calculate
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def calculate
    @square_of_sums = 0
    @sum_of_squares = 0

    1.upto(@n) do |i|
      @square_of_sums += i
      @sum_of_squares += i ** 2
    end

    @square_of_sums **= 2
  end
end
