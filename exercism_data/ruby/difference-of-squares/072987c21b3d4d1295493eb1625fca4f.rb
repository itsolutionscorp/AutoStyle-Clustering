class Squares
  def initialize(num)
    @loop = num
    @sum_of_squares = 0
    @square_of_sums = 0
  end

  def sum_of_squares
    for i in 0..@loop
      @sum_of_squares += i ** 2
    end
    @sum_of_squares
  end

  def square_of_sums
    for i in 0..@loop
      @square_of_sums += i
    end
    @square_of_sums ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
