class Squares
  def initialize(number)
    @number = number + 1
    @sum_squares = 0
    @square_sums = 0
  end

  def sum_of_squares
    @number.times do |nat_num|
      @sum_squares += (nat_num ** 2)
    end
    @sum_squares
  end

  def square_of_sums
    @number.times do |nat_num|
      @square_sums += nat_num
    end
    @square_sums = @square_sums ** 2
  end

  def difference
    sum_of_squares
    square_of_sums
    @square_sums - @sum_squares
  end
end
