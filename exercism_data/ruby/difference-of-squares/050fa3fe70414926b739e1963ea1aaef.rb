class Squares
  attr_reader :sum_of_squares, :square_of_sums

  def initialize(number)
    @sum_of_squares = []
    @square_of_sums = 0
    calc_squares(number)
  end

  def difference
    @square_of_sums - @sum_of_squares
  end

  private

  def calc_squares(n)
    # iterator starts from 0 so we need n+1 cycles
    (n+1).times do |number|
      @square_of_sums+=number
      @sum_of_squares << number**2
    end
    @sum_of_squares = @sum_of_squares.reduce(:+)
    @square_of_sums**=2
  end

end
