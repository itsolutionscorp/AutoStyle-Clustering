class Squares
  attr_reader :num

  def initialize(num)
    @num = num
    @sum = 0
    @square_of_sums = 0
    @sum_of_squares = 0
  end

  def square_of_sums
    (1..@num).each do |i|
      @sum += i
    end
    @square_of_sums = (@sum**2)
  end

  def sum_of_squares
    (1..@num).each do |i|
      @sum_of_squares += (i**2)
    end
    @sum_of_squares
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
