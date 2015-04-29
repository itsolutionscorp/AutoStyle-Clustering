class Squares
  def initialize num
    @num = num
  end

  def square_of_sums
    @square_of_sums = 0
    (1..@num).each do |num|
      @square_of_sums += num
    end
    @square_of_sums = @square_of_sums*@square_of_sums
    @square_of_sums
  end

  def sum_of_squares
    @sum_of_squares = 0
    (1..@num).each do |num|
      @sum_of_squares += num*num
    end
    @sum_of_squares
  end

  def difference
    difference = square_of_sums - sum_of_squares
    difference
  end
end
