# Difference between Squares
class Squares
  attr_accessor :sum_of_squares, :square_of_sums

  def initialize(number)
    @sum_of_squares = 0
    sum_of_nums = 0

    (1..number).each do |num|
      @sum_of_squares += num**2
      sum_of_nums += num
    end
    @square_of_sums = sum_of_nums**2
  end

  def difference
    @square_of_sums - @sum_of_squares
  end
end
