class Squares
  def initialize(n)
    @square_of_sums = (1..n).inject {|r,i| r += i    } ** 2
    @sum_of_squares = (1..n).inject {|r,i| r += i**2 }
    @difference     = @square_of_sums - @sum_of_squares
  end

  attr_reader :square_of_sums
  attr_reader :sum_of_squares
  attr_reader :difference
end
