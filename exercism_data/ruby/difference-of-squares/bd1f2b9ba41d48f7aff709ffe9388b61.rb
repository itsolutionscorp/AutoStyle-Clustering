class Squares
  attr_reader :sum_of_squares
  attr_reader :square_of_sums
  attr_reader :difference

  def initialize(number)
    range           = (1..number)
    @square_of_sums = sum_of(range) ** 2

    squares         = range.map {|n| n**2}
    @sum_of_squares = sum_of(squares)

    @difference     = @square_of_sums - @sum_of_squares
  end

  private

    def sum_of(range)
      range.reduce(:+) # synonym of 'inject'
    end
end
